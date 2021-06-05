package com.bookstore.dao;

import com.bookstore.entity.Book;
import java.util.List;

public interface BookDao {
    Book findOne(Long id);
    List<Book> getBooks();
    Book changeInventory(Long id, int inventory);
    Book save(Book book);
}
