package edu.unimagdalena.bookstore.seeder;

import java.util.ArrayList;
import java.util.Locale;

import com.github.javafaker.Faker;

import edu.unimagdalena.bookstore.entity.Publisher;
import edu.unimagdalena.bookstore.repository.PublisherRepository;

public class PublisherTableSeeder {
	
	Faker faker = new Faker(new Locale("es-MX"));
	
	private PublisherRepository publisherRepository;
	
	public PublisherTableSeeder(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}
	
	public ArrayList<Publisher> run() {
		ArrayList<Publisher> publisherList = new ArrayList<Publisher>();
		
		Publisher at = new Publisher();
		at.setName(faker.book().publisher());
		publisherRepository.save(at);
		
		publisherList.add(at);
		
		return publisherList;
	}
	
	public ArrayList<Publisher> run(Integer n) {
		ArrayList<Publisher> publisherList = new ArrayList<Publisher>();
		
		for (int i = 0; i < n; i++) {
			Publisher at = new Publisher();
			at.setName(faker.book().publisher());
			
			publisherList.add(at);
		}
		
		publisherRepository.saveAll(publisherList);
		
		return publisherList;
	}
}
