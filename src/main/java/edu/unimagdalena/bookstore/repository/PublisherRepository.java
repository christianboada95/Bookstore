package edu.unimagdalena.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.bookstore.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
