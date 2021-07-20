package edu.unimagdalena.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.bookstore.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Category findByName(String name);
}
