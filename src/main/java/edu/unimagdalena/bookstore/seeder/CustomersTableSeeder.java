package edu.unimagdalena.bookstore.seeder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.javafaker.Faker;

import edu.unimagdalena.bookstore.entity.Category;
import edu.unimagdalena.bookstore.entity.Card;
import edu.unimagdalena.bookstore.entity.users.Customer;
import edu.unimagdalena.bookstore.repository.CustomerRepository;

public class CustomersTableSeeder {

	private Faker faker = new Faker(new Locale("es-MX"));

	private CustomerRepository customerRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public CustomersTableSeeder(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	public ArrayList<Customer> run() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		Category category = new Category(faker.book().genre());

		Customer c = new Customer(category);
		c.setUsername("christianboada");
		c.setEmail("cristian@tayrosoft.com");
		c.setPassword(bCryptPasswordEncoder.encode("secret123"));
		c.setName("Cristian");
		c.setSurname("Boada");
		c.setAddress("Cra 12B #3-27");
		c.setPostcode("14");
		c.setDepartment("Magdalena");
		c.setCity("Santa Marta");
		c.setImage("avatar.png");

		Card card = new Card();
		card.setNumber("123456789");
		card.setType("Credito");
		card.setEmisor("Master Card");
		card.setExpires_at(new Date());
		card.setCvv("1234");
		c.setCard(card);

		customerList.add(c);

		customerRepository.save(c);

		return customerList;
	}
}
