package com.bookstore.dao;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.node.BookNode;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;

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
    Page<Book> getBookByPage(int pageIndex, int pageSize);
    Long count();
    Book addRemark(Long id, String remark);
    List<Book> findByTwoRelationship(String name);
}
