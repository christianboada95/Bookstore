package edu.unimagdalena.bookstore.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unimagdalena.bookstore.entity.Book;
import edu.unimagdalena.bookstore.entity.Order;
import edu.unimagdalena.bookstore.entity.OrderDetail;
import edu.unimagdalena.bookstore.model.OrderModel;
import edu.unimagdalena.bookstore.repository.BookRepository;
import edu.unimagdalena.bookstore.repository.OrderRepository;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	BookRepository bookRepository;

	@GetMapping("/orders")
	public List<Order> getOrders() {
		// Return all orders in database
		return orderRepository.findAll();
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable(value = "id") Integer id) {
		// Get a order by id
		Optional<Order> o = orderRepository.findById(id);

		// Evaluate if exists
		if (!o.isPresent()) {
			// Return 404
			return ResponseEntity.notFound().build();
		}

		// Return the Order object
		return ResponseEntity.ok().body(o.get());
	}

	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody OrderModel o) {
		// Create a list of books
		ArrayList<Book> products = new ArrayList<Book>();
		Integer total = 0;

		for (OrderDetail detail : o.details) {
			Book b = detail.getBook();

			// Calculate total price of order
			total += b.getPrice() * detail.getQuantity();

			// Calculate new stock value
			Integer stock = b.getStock() - detail.getQuantity();

			// Validate if is possible
			if (stock >= 0) {
				b.setStock(stock);
				products.add(b);
			} else {
				return ResponseEntity.badRequest().build();
			}

		}

		// Save new Stock values
		bookRepository.saveAll(products);

		// create a new order
		Order order = new Order(o.client, o.details);

		String code = Long.toString(Instant.now().toEpochMilli());
		// order.setCode(RandomStringUtils.randomAlphanumeric(10));
		order.setCode(code);
		order.setTotal(total);
		order.setDate(new Date());
		order.setShipping(o.shipping);
		o.card.setExpires_at(new Date());
		order.setCard(o.card);

		orderRepository.save(order);
		return ResponseEntity.ok().body(order);
	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable Integer id) {
		// Get a Order by id
		Optional<Order> prod = orderRepository.findById(id);

		// Evaluate if exists
		if (!prod.isPresent()) {
			// Return 404
			return ResponseEntity.notFound().build();
		}

		// Remove the Order from database
		orderRepository.delete(prod.get());

		return ResponseEntity.noContent().build();
	}

}
