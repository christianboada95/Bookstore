package edu.unimagdalena.bookstore.seeder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

import edu.unimagdalena.bookstore.entity.Author;
import edu.unimagdalena.bookstore.entity.Book;
import edu.unimagdalena.bookstore.entity.Category;
import edu.unimagdalena.bookstore.entity.Publisher;
import edu.unimagdalena.bookstore.repository.AuthorRepository;
import edu.unimagdalena.bookstore.repository.BookRepository;
import edu.unimagdalena.bookstore.repository.CategoryRepository;
import edu.unimagdalena.bookstore.repository.PublisherRepository;

public class BooksTableSeeder {

	private Faker faker = new Faker(new Locale("es-MX"));

	private BookRepository bookRepository;
	private AuthorRepository authorRepository;
	private PublisherRepository publisherRepository;
	private CategoryRepository categoryRepository;

	public BooksTableSeeder(BookRepository bookRepository, AuthorRepository authorRepository,
			PublisherRepository publisherRepository, CategoryRepository categoryRepository) {

		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.publisherRepository = publisherRepository;
		this.categoryRepository = categoryRepository;
	}

	// Basic run
	public ArrayList<Book> run() {
		ArrayList<Book> bookList = new ArrayList<Book>();

		Category best = new Category("Best seller");
		Category news = new Category("News");
		Category Science = new Category("Science fiction");

		Author at = new Author();
		at.setName(faker.book().author());
		authorRepository.save(at);

		Publisher ps = new Publisher();
		ps.setName(faker.book().publisher());
		publisherRepository.save(ps);

		Book b = new Book(at, ps, best);
		b.setISBN(faker.letterify("B??kst?r??????"));
		b.setTitle(faker.book().title());
		b.setDescription(faker.lorem().paragraph());
		b.setCover("http://localhost:8080/img/" + "portada.jpg");
		b.setPages(faker.number().numberBetween(50, 500));
		b.setPrice(faker.number().numberBetween(20000, 150000));
		b.setQualification(faker.number().numberBetween(0, 5));
		b.setRelease_at(faker.date().birthday());
		b.setStock(faker.number().numberBetween(5, 15));

		bookList.add(b);

		bookRepository.save(b);

		return bookList;
	}

	public ArrayList<Book> run(Integer n) {
		ArrayList<Book> bookList = new ArrayList<Book>();
		List<Category> categoryList = new ArrayList<Category>();

		// add default categories
		categoryList.add(new Category("Best seller"));
		categoryList.add(new Category("New"));

		// Add random categories
		for (int i = 0; i < n / 2; i++) {
			categoryList.add(new Category(faker.book().genre()));
		}

		// Create N books
		for (int i = 0; i < n; i++) {

			// Select random categories for a new book
			Integer nc = faker.number().numberBetween(1, 3);
			Category[] categories = new Category[nc];
			// long size = categoryRepository.count();
			for (int j = 0; j < categories.length; j++) {
				// category = new Category(faker.book().genre());
				categories[j] = categoryList.get(faker.number().numberBetween(0, categoryList.size()));

				// categories[j] = categoryRepository.findById(
				// (int) faker.number().numberBetween(1, size)).get();
			}

			// select random publisher
			long max = publisherRepository.count();
			Publisher ps = publisherRepository.findById((int) faker.number().numberBetween(1, max)).get();
			// select random author
			max = authorRepository.count();
			Author at = authorRepository.findById((int) faker.number().numberBetween(1, max)).get();

			// Create a new book
			Book b = new Book(at, ps, categories);
			b.setISBN(faker.letterify("B??kst?r??????"));
			b.setTitle(faker.book().title());
			b.setDescription(faker.lorem().paragraph());
			b.setCover("http://localhost:8080/img/" + "cover-" + faker.number().numberBetween(2, 10) + ".jpg");
			b.setPages(faker.number().numberBetween(50, 500));
			b.setPrice(faker.number().numberBetween(20000, 150000));
			b.setQualification(faker.number().numberBetween(0, 5));
			b.setRelease_at(faker.date().birthday());
			b.setStock(faker.number().numberBetween(5, 15));

			bookList.add(b);

		}

		bookRepository.saveAll(bookList);

		return bookList;
	}

}
