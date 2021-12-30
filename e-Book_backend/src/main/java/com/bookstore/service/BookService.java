package com.bookstore.service;

import com.bookstore.dto.DataPage;
import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    Book findBookById(Long id);
    Book changeInventory(Long id, int inventory);
    List<Book> getBooks();
    Book changePrice(Long id, BigDecimal price);
    Book changeAuthor(Long id, String author);
    Book changeISBN(Long id, String isbn);
    Book changeDescription(Long id, String description);
    Book changeName(Long id, String name);
    Book addBook(String name, String author, BigDecimal price, int inventory, String description, String type, String image, String isbn);
    void delete(Long id);
    DataPage<Book> GetBookPage(int pageNumber);
    Book addRemark(Long id, String remark);
    List<Book> findByTwoRelationship(String bookName);
}
