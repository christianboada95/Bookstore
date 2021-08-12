package edu.unimagdalena.bookstore.entity.users;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.unimagdalena.bookstore.entity.Category;
import edu.unimagdalena.bookstore.entity.Card;
import edu.unimagdalena.bookstore.entity.Order;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false, length = 16)
	private String username;

	@Email
	@Column(unique = true, nullable = false, length = 128)
	private String email;

	@Column(unique = true, nullable = false, length = 128)
	private String password;

	private String name;
	private String surname;
	private String address;
	private String postcode;
	private String department;
	private String city;
	private String image;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "customer_category", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Set<Category> categories;

	@JsonIgnore
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private Set<Order> order;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(unique = true, nullable = true)
	private Card card;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Card card) {
		this.card = card;
		this.card.setCustomer(this);
	}

	public Customer(Category... categories) {
		this.categories = Stream.of(categories).collect(Collectors.toSet());
		this.categories.forEach(x -> x.getCustomers().add(this));
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}
