package edu.unimagdalena.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unimagdalena.bookstore.entity.Book;
import edu.unimagdalena.bookstore.entity.Category;
import edu.unimagdalena.bookstore.repository.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public List<Category> getCategories() {
		// Return all books in database
		return categoryRepository.findAll();
	}
	
}
