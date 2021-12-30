package com.bookstore.serviceimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.dto.DataPage;
import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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
    @Cacheable(value = "book", key = "#id")
    public Book findBookById(Long id){
        System.out.println("执行了查询 id = "+ id);
        return bookDao.findOne(id);
    }
    @Override
    @CachePut(value = "book", key = "#id")
    public Book changeInventory(Long id, int inventory) {
        System.out.println("执行了修改库存 id = "+ id);
        return bookDao.changeInventory(id, inventory);
    }
    @Override
    @CachePut(value = "book", key = "#id")
    public Book changePrice(Long id, BigDecimal price) {
        return bookDao.changePrice(id, price);
    }
    @Override
    @CachePut(value = "book", key = "#id")
    public Book changeAuthor(Long id, String author) {
        return bookDao.changeAuthor(id, author);
    }
    @Override
    @CachePut(value = "book", key = "#id")
    public Book changeISBN(Long id, String isbn) {
        return bookDao.changeISBN(id, isbn);
    }
    @Override
    @CachePut(value = "book", key = "#id")
    public Book changeDescription(Long id, String description) {
        return bookDao.changeDescription(id, description);
    }
    @Override
    @CachePut(value = "book", key = "#id")
    public Book changeName(Long id, String name) {
        return bookDao.changeName(id, name);
    }
    @Override
    public Book addBook(String name, String author, BigDecimal price, int inventory, String description, String type, String image, String isbn) {
        Book book = new Book(name, author, price, inventory, description, type, image, isbn);
        return bookDao.save(book);
    }
    @Override
    @CacheEvict(value = "book", key = "#id")
    public void delete(Long id) {
        bookDao.delete(id);
    }
    @Override
    public DataPage<Book> GetBookPage(int pageNumber) {
        //查询当前表的所有记录数
        Long totalRecord = bookDao.count();
        //调用有参构造函数，创建page对象
        DataPage<Book> page = new DataPage<Book>(totalRecord.intValue(), pageNumber);
        //第三步，查询分页列表数据并设置到page对象中
        if (pageNumber < 0 || page.getPageNumber() > page.getTotalPage()) {
            return page;
        }
        Page<Book> bookPage = bookDao.getBookByPage(pageNumber,page.getPageSize());
        page.setData(bookPage.toList());
        return page;
    }
    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Book addRemark(Long id, String remark) {
        return bookDao.addRemark(id, remark);
    }

    @Override
    public List<Book> findByTwoRelationship(String bookName) {
        return bookDao.findByTwoRelationship(bookName);
    }
}
