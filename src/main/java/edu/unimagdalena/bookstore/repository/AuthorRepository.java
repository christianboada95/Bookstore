package edu.unimagdalena.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.bookstore.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
