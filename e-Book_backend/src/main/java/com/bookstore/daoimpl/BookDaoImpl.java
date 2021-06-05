package com.bookstore.daoimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import static java.lang.Integer.max;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Long id){
        return bookRepository.getBookById(id);
    }


    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book changeInventory(Long id, int inventory) {
        Book book = findOne(id);
        book.setInventory(max(inventory, 0));
        return bookRepository.save(book);
    }
    @Override
    public Book save(Book book) { return bookRepository.save(book); }

}
