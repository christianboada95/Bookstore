package edu.unimagdalena.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.bookstore.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

}
