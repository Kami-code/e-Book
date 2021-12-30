package com.bookstore.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name="BOOK")
@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String author;
    private BigDecimal price;
    private int inventory;
    private String description;
    private String type;
    private String image;
    private String isbn;

    @Transient
    private Remark remark;
    public void setRemark(Remark remark) {
        this.remark = remark;
    }
    public Remark getRemark() {
        return remark;
    }

    public Book(String name, String author, BigDecimal price, int inventory, String description, String type, String image, String isbn) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
        this.type = type;
        this.image = image;
        this.isbn = isbn;
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

