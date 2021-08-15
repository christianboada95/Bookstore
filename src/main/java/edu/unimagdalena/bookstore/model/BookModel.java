package edu.unimagdalena.bookstore.model;

import edu.unimagdalena.bookstore.entity.Book;

import java.util.Date;

import edu.unimagdalena.bookstore.entity.Author;
import edu.unimagdalena.bookstore.entity.Publisher;

public class BookModel {

    public String isbn;
    public String title;
    public Integer stock;
    public Integer price;
    public String description;
    // public String cover;
    public Integer pages;
    // private Integer qualification;
    // private Date release_at;

    public String author;

    public String publisher;

    public BookModel() {
    }

}
