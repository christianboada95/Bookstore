package edu.unimagdalena.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.bookstore.entity.users.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	Customer findByUsername(String username);
	
}
