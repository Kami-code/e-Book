package com.bookstore.service;

import com.bookstore.entity.Book;

import java.util.List;

public interface BookService {

    Book findBookById(Long id);
    Book changeInventory(Long id, int inventory);
    List<Book> getBooks();
}
