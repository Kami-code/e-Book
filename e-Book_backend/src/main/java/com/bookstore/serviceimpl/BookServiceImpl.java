package com.bookstore.serviceimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Long id){
        return bookDao.findOne(id);
    }
    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }
    @Override
    public Book changeInventory(Long id, int inventory) {return bookDao.changeInventory(id, inventory); }
    @Override
    public Book changePrice(Long id, BigDecimal price) {
        return bookDao.changePrice(id, price);
    }
    @Override
    public Book changeAuthor(Long id, String author) {
        return bookDao.changeAuthor(id, author);
    }
    @Override
    public Book changeISBN(Long id, String isbn) {
        return bookDao.changeISBN(id, isbn);
    }
    @Override
    public Book changeDescription(Long id, String description) {
        return bookDao.changeDescription(id, description);
    }
    @Override
    public Book changeName(Long id, String name) {
        return bookDao.changeName(id, name);
    }
    @Override
    public Book addBook(String name, String author, BigDecimal price, int inventory, String description, String type, String image, String isbn) {
        Book book = new Book(name, author, price, inventory, description, type, image, isbn);
        return bookDao.save(book);
    }
    @Override
    public void delete(Long id) {
        bookDao.delete(id);
    }
}
