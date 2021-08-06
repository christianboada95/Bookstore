package edu.unimagdalena.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unimagdalena.bookstore.entity.users.Customer;
import edu.unimagdalena.bookstore.repository.CustomerRepository;

@RestController
@RequestMapping("api")
public class CustomerController {

	private CustomerRepository customerRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public CustomerController(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.customerRepository = customerRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@PostMapping("/customers")
	public void saveUsuario(@RequestBody Customer user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		customerRepository.save(user);
	}

	@GetMapping("/customers")
	public List<Customer> getAllUsuarios() {
		return customerRepository.findAll();
	}

	@GetMapping("/customers/{username}")
	public Customer getUsuario(@PathVariable String username) {
		return customerRepository.findByUsername(username);

	}

	@PutMapping("/customers/{username}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable String username, @RequestBody Customer user) {
		Customer customer = customerRepository.findByUsername(username);

		if (customer == null) {
			return ResponseEntity.notFound().build();
		}

		customer.setName(user.getName());
		customer.setSurname(user.getSurname());
		customer.setEmail(user.getEmail());
		// customer.setUsername(user.getUsername());
		// customer.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		customer.setCity(user.getCity());
		customer.setDepartment(user.getDepartment());
		customer.setAddress(user.getAddress());
		customer.setPostcode(user.getPostcode());
		// customer.setCard(user.getCard());
		// customer.setCategories(user.getCategories());

		return ResponseEntity.ok(customerRepository.save(customer));
	}

}
