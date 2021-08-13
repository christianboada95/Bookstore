package edu.unimagdalena.bookstore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unimagdalena.bookstore.entity.users.Customer;
import edu.unimagdalena.bookstore.repository.CustomerRepository;
import edu.unimagdalena.bookstore.validator.CustomerValidator;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/user")
    public Customer user(Principal principal) {

        String username = principal.getName();
        return customerRepository.findByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registration(@RequestBody Customer user, BindingResult bindingResult) {
        customerValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        customerRepository.save(user);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/logout")
    public String logout() {
        return "auth/login";
    }

}
