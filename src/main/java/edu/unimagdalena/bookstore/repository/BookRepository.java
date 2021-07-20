package edu.unimagdalena.bookstore.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.bookstore.entity.Book;
import edu.unimagdalena.bookstore.entity.Category;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	List<Book> findByTitleContaining(String title);
	List<Book> findByTitleContainingAndIsbnContaining(String title, String isbn);
	List<Book> findByTitleContainingAndIsbnContainingAndAuthor_nameContaining(String title, String isbn, String name);
	List<Book> findByTitleContainingAndIsbnContainingAndAuthor_nameContainingOrCategoriesContaining(String title, String isbn, String name, Set<Category> categories);
	/** Testing */
	List<Book> findByCategories_nameContaining(String name);
}
