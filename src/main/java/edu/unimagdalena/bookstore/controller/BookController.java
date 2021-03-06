package edu.unimagdalena.bookstore.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.unimagdalena.bookstore.entity.Book;
import edu.unimagdalena.bookstore.entity.Category;
import edu.unimagdalena.bookstore.repository.*;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//@Autowired
	//private CategoryRepository catego

	@GetMapping("/books")
	public List<Book> getBooks() {
		// Return all books in database
		return bookRepository.findAll();
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Integer id) {
		// Get a book  by id
		Optional<Book> b = bookRepository.findById(id);
		
		// Evaluate if exists
		if (!b.isPresent()) {
			// Return 404
			return ResponseEntity.notFound().build();
		}
		
		// Return the Book object
		return ResponseEntity.ok().body(b.get());
	}
	
	@GetMapping("/books/search")
	public List<Book> searchBooks(Principal principal,
								  @RequestParam(value = "title", required = false, defaultValue = "") String title,
								  @RequestParam(value = "isbn", required = false, defaultValue = "") String isbn,
								  @RequestParam(value = "author", required = false, defaultValue = "") String author) {
		// Create a new empty list of books
		List<Book> searchResults = null;
		
		// Categorias seleccionadas del usuario
		//String username = principal.getName();
		//Set<Category> categories = customerRepository.findByUsername(username).getCategories();
		
		// sino tiene entonces no es usuario especializado
		/*if(categories.isEmpty()) {
			// Categor??as por defecto
			//System.out.println("Aqui si entr??");
			categories.add(categoryRepository.findByName("Best seller"));
			categories.add(categoryRepository.findByName("New"));
		}*/
		
		
		// Try get book with the parameters
		try {
			searchResults = bookRepository.findByTitleContaining(title);
			//searchResults = bookRepository.findByTitleContainingAndIsbnContaining(title, isbn);
			searchResults = bookRepository.findByTitleContainingAndIsbnContainingAndAuthor_nameContaining(title, isbn, author);
			//searchResults = bookRepository.findByTitleContainingAndIsbnContainingAndAuthor_nameContainingOrCategoriesContaining(title, isbn, author, categories);
			//searchResults = bookRepository.findByCategories_nameContaining(category);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// Return filtered books
		return searchResults;
	}

}
