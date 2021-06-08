package com.bookstore.daoimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    @Override
    public Book changePrice(Long id, BigDecimal price) {
        Book book = findOne(id);
        book.setPrice(price);
        return bookRepository.save(book);
    }
    @Override
    public Book changeAuthor(Long id, String author) {
        Book book = findOne(id);
        book.setAuthor(author);
        return bookRepository.save(book);
    }
    @Override
    public Book changeISBN(Long id, String isbn) {
        Book book = findOne(id);
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
    @Override
    public Book changeDescription(Long id, String description) {
        Book book = findOne(id);
        book.setDescription(description);
        return bookRepository.save(book);
    }
    @Override
    public Book changeName(Long id, String name) {
        Book book = findOne(id);
        book.setName(name);

        return bookRepository.save(book);
    }
    @Override
    public void delete(Long id) {
        bookRepository.deleteBookById(id);
    }

}
