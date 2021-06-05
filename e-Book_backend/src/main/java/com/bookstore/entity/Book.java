package com.bookstore.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name="BOOK")
@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String isbn;
    private String name;
    private String type;
    private String author;
    private BigDecimal price;
    private String description;
    private int inventory;
    private String image;


    public Book(String a, String n, String t, BigDecimal p, String de, int in, String im) {
        isbn = a;
        name = n;
        type = t;
        author = t;
        price = p;
        description = de;
        inventory = in;
        image = im;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }
    public int getInventory() {
        return inventory;
    }
    public String getAuthor() {
        return author;
    }
    public String getImage() {
        return image;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setId(Long i) {id = i;}
}

