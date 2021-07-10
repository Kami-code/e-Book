package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public void deleteBookById(Long id);
    Iterable<Book> findAll(Sort sort);
    Page<Book> findAll(Pageable pageable);
}
