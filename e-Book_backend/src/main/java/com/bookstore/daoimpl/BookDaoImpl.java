package com.bookstore.daoimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Remark;
import com.bookstore.entity.User;
import com.bookstore.node.BookNode;
import com.bookstore.repository.BookNodeRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.RemarkRepository;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.max;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RemarkRepository remarkRepository;
    @Autowired
    private BookNodeRepository bookNodeRepository;

    @Override
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Book findOne(Long id){
        Book book = bookRepository.getBookById(id);
        Optional<Remark> remark = remarkRepository.findById(id);
        if (remark.isPresent()){
//            System.out.println("Not Null " + id);
            book.setRemark(remark.get());
        }
        else{
//            System.out.println("It's Null");
        }
        return book;
    }


    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Book changeInventory(Long id, int inventory) {
        Book book = findOne(id);
        book.setInventory(max(inventory, 0));
        return bookRepository.save(book);
    }


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

    @Override
    public Page<Book> getBookByPage(int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public Long count() {
        return bookRepository.count();
    }

    @Override
    public Book save(Book book) {
        Remark remark = book.getRemark();
        remarkRepository.save(remark);
        return bookRepository.save(book);
    }

    @Override
    public Book addRemark(Long book_id, String remark) {
        Book book = findOne(book_id);
        Remark remarks = book.getRemark();
        if (remarks == null) {
            remarks = new Remark(book.getId());
        }
        else {
            remarkRepository.delete(book.getRemark());
        }
        remarks.addRemark(remark);
        book.setRemark(remarks);
        save(book);
        return book;
    }

    @Override
    public List<Book> findByTwoRelationship(String name) {
        System.out.println("name = "+ name);
        List<Book> result = new ArrayList<>();
        List<BookNode> bookNodes = bookNodeRepository.findByTwoRelationship(name);
        for(BookNode tmpBookNode:bookNodes) {
            Book tmpBook = bookRepository.findByName(tmpBookNode.getName());
            if (tmpBook != null) {
                result.add(tmpBook);
            }
        }
        return result;
    }
}
