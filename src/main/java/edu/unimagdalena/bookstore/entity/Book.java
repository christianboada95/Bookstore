package edu.unimagdalena.bookstore.entity;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false, length = 32)
	private String isbn;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private Integer stock;
	@Column(nullable = false)
	private Integer price;

	@Column(nullable = true)
	private String description;
	private String cover;
	private Integer pages;
	private Integer qualification;
	private Date release_at;

	@ManyToOne
	@JoinColumn
	private Author author;

	// @JsonIgnore
	// @JsonBackReference(value = "publisherbooks")
	// @JsonManagedReference(value = "publisherbooks")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Publisher publisher;

	// @JsonIgnore
	// @JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Set<Category> categories;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Set<OrderDetail> details = new HashSet<OrderDetail>();

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String isbn, String title, Author author, Publisher publisher, Set<Category> categories) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.categories = categories;
	}

	public Book(Author author, Publisher publisher, Category... categories) {

		this.author = author;
		this.publisher = publisher;
		this.categories = Stream.of(categories).collect(Collectors.toSet());
		this.categories.forEach(x -> x.getBooks().add(this));
	}

	public Book(Category... categories) {

		this.categories = Stream.of(categories).collect(Collectors.toSet());
		this.categories.forEach(x -> x.getBooks().add(this));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getQualification() {
		return qualification;
	}

	public void setQualification(Integer qualification) {
		this.qualification = qualification;
	}

	public Date getRelease_at() {
		return release_at;
	}

	public void setRelease_at(Date release_at) {
		this.release_at = release_at;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}
