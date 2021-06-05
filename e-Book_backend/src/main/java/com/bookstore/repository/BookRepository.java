package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    public List<Book> findByName(String n);
    public List<Book> findByAuthor(String a);
    public Book getBookById(Long id);
    @Override
    public List<Book> findAll();
}
