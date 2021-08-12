package edu.unimagdalena.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.bookstore.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
