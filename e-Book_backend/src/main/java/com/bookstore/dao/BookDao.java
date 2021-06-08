package com.bookstore.dao;

import com.bookstore.entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookDao {
    Book findOne(Long id);
    List<Book> getBooks();
    Book changeInventory(Long id, int inventory);
    Book changePrice(Long id, BigDecimal price);
    Book changeAuthor(Long id, String author);
    Book changeISBN(Long id, String isbn);
    Book changeDescription(Long id, String description);
    Book changeName(Long id, String name);
    Book save(Book book);
    void delete(Long id);
}
