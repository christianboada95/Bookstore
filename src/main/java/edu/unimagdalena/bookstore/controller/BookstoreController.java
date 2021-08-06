package edu.unimagdalena.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.unimagdalena.bookstore.entity.users.Customer;

@Controller
public class BookstoreController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/store")
	public String store() {
		return "index";
	}

	@GetMapping("/book")
	public String book() {
		return "book/index";
	}
	
	@GetMapping("/cart")
	public String cart() {
		return "book/cart";
	}
	
	@GetMapping("/auth/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("/auth/register")
	public String register() {
		return "auth/register";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "user/profile";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin/admin";
	}
}
