package edu.unimagdalena.bookstore.seeder;

import java.util.ArrayList;
import java.util.Locale;

import com.github.javafaker.Faker;

import edu.unimagdalena.bookstore.entity.Author;
import edu.unimagdalena.bookstore.repository.AuthorRepository;

public class AuthorTableSeeder {
	
	Faker faker = new Faker(new Locale("es-MX"));
	
	private AuthorRepository authorRepository;
	
	public AuthorTableSeeder(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	public ArrayList<Author> run() {
		ArrayList<Author> authorList = new ArrayList<Author>();
		
		Author at = new Author();
		at.setName(faker.book().author());
		authorRepository.save(at);
		
		authorList.add(at);
		
		return authorList;
	}
	
	public ArrayList<Author> run(Integer n) {
		ArrayList<Author> authorList = new ArrayList<Author>();
		
		for (int i = 0; i < n; i++) {
			Author at = new Author();
			at.setName(faker.book().author());
			
			authorList.add(at);
		}
		
		authorRepository.saveAll(authorList);
		
		return authorList;
	}
}