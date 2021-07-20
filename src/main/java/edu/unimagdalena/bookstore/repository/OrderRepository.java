package edu.unimagdalena.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.bookstore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
