package edu.unimagdalena.bookstore.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import edu.unimagdalena.bookstore.repository.AuthorRepository;
import edu.unimagdalena.bookstore.repository.BookRepository;
import edu.unimagdalena.bookstore.repository.CategoryRepository;
import edu.unimagdalena.bookstore.repository.CustomerRepository;
import edu.unimagdalena.bookstore.repository.OrderRepository;
import edu.unimagdalena.bookstore.repository.PublisherRepository;

@Component
public class DataBaseSeeder {
	
	private CustomerRepository customerRepository;
	private BookRepository bookRepository;
	private OrderRepository orderRepository;
	private AuthorRepository authorRepository;
	private PublisherRepository publisherRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public DataBaseSeeder(CustomerRepository customerRepository,
						BookRepository bookRepository,
						OrderRepository orderRepository,
						AuthorRepository authorRepository,
						PublisherRepository publisherRepository,
						CategoryRepository categoryRepository) {
		
		this.customerRepository = customerRepository;
		this.bookRepository = bookRepository;
		this.orderRepository = orderRepository;
		this.authorRepository = authorRepository;
		this.publisherRepository = publisherRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		System.out.println("Sedding database");
		seedCustomersTable();
		seedAuthorTable();
		seedPublisherTable();
		seedBooksTable();
	}
	
	private void seedPublisherTable() {
		System.out.println("Sedding publishers table...");
		PublisherTableSeeder ps = new PublisherTableSeeder(publisherRepository);
		ps.run(50);
	}

	private void seedAuthorTable() {
		System.out.println("Sedding authors table...");
		AuthorTableSeeder as = new AuthorTableSeeder(authorRepository);
		as.run(10);
	}

	private void seedCustomersTable() {
		System.out.println("Sedding customers table...");
		CustomersTableSeeder cs = new CustomersTableSeeder(customerRepository);
		cs.run();
	}
	
	private void seedBooksTable() {
		System.out.println("Sedding books table...");
		BooksTableSeeder bs = new BooksTableSeeder(bookRepository, authorRepository, publisherRepository, categoryRepository);
		bs.run(20);
	}
}
