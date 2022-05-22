package com.bookstore.serviceimpl;


import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.*;
import com.bookstore.service.CartService;
import com.bookstore.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CartServiceImpl implements CartService {
    ConcurrentHashMap<Long, Cart> cartMap = new ConcurrentHashMap<>();

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    WebApplicationContext applicationContext;

    @Override
    public Cart addBooksToCartSession(long bookId, Long user_id) {
        HttpSession session = SessionUtil.getSession();
//        Cart cart = (Cart)session.getAttribute("cart");
        Cart cart = cartMap.get(user_id);
        if (cart == null) {
            cart = new Cart(user_id);
        }
        System.out.println("the cart in session" + cart.toString());
        cart.addBookToCart(bookId);
        cartMap.put(user_id, cart);
//        session.setAttribute("cart", cart);
        return cart;
    }

    @Override
    public Cart getCart(Long user_id) {
        HttpSession session = SessionUtil.getSession();
        Cart cart = cartMap.get(user_id);
        if (cart == null) {
            cart = new Cart(user_id);
        }
        cartMap.put(user_id, cart);
//        Cart cart = (Cart)session.getAttribute("cart");
//        if (cart == null) {
//            cart = new Cart((Long) session.getAttribute("user_id"));
//        }
        return cart;
    }

    @Override
    public String placeOrder(Long user_id) {
        HttpSession session = SessionUtil.getSession();
        Cart cart = cartMap.get(user_id);
        if (cart == null) { return "Cart is null"; }

        Map<Long, Integer> cart_instance = cart.getInstance();
        User user = userDao.getUserById(user_id);
        Order_master order = new Order_master(new BigDecimal(0), 1, user);
        for (Long book_id : cart_instance.keySet()) {
            Integer book_count = cart_instance.get(book_id);
            Book book = bookDao.findOne(book_id);
            if (book == null) {
                return "Book not find";
            }
            BigDecimal price = book.getPrice();
            int last_inventory = book.getInventory() - book_count;
            if (last_inventory >= 0) {
                BigDecimal discount = BigDecimal.valueOf(Math.random());
                bookDao.changeInventory(book.getId(), last_inventory);
                order.addOrderItem(new Order_item(book, book_count, price, discount, price.multiply(new BigDecimal(book_count)).subtract(discount), order));
            }
            else { //the book inventory is none
                return "Book not available";
            }
        }
        orderDao.save(order);


//        jmsTemplate.convertAndSend("order", cart);
        cartMap.remove(user_id);
        return "Success";
    }
}
