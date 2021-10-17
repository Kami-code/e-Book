package com.bookstore.serviceimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.*;
import com.bookstore.service.OrderHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

public class OrderHandlerServiceImpl implements OrderHandlerService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    @JmsListener(destination = "order")//, containerFactory = "myFactory")
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
    public void receiveMessage(Cart cart) throws Exception {
        Long user_id = cart.getUser_id();
        Map<Long, Integer> cart_instance = cart.getInstance();
        User user = userDao.getUserById(user_id);
        Order_master order = new Order_master(new BigDecimal(0), 1, user);
        for (Long book_id : cart_instance.keySet()) {
            Integer book_count = cart_instance.get(book_id);
            Book book = bookDao.findOne(book_id);
            if (book == null) {
                throw new Exception();
            }
            BigDecimal price = book.getPrice();
            int last_inventory = book.getInventory() - book_count;
            if (last_inventory >= 0) {
                BigDecimal discount = BigDecimal.valueOf(Math.random());
                bookDao.changeInventory(book.getId(), last_inventory);
                order.addOrderItem(new Order_item(book, book_count, price, discount, price.multiply(new BigDecimal(book_count)).subtract(discount), order));
            }
            else { //the book inventory is none
                throw new Exception();
            }
        }
        orderDao.save(order);
    }
}
