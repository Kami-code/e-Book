package com.bookstore.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.UserDao;
import com.bookstore.dto.UserDto;
import com.bookstore.entity.*;
import com.bookstore.service.OrderService;
import com.bookstore.util.SessionUtil;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    WebApplicationContext applicationContext;

    @Override
    public boolean placeOrder() {
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        HttpSession session = SessionUtil.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            return false;
        }
        jmsTemplate.convertAndSend("order", cart);
        session.removeAttribute("cart");
        return true;
    }

    @Override
    @JmsListener(destination = "order")//, containerFactory = "myFactory")
    public void receiveMessage(Cart cart) {
        System.out.println("Received <" + cart.toString() + ">");
        Long user_id = cart.getUser_id();
        User user = userDao.getUserById(user_id);
        Map<Long, Integer> cart_instance = cart.getInstance();
        BigDecimal total_price = new BigDecimal(0);
        Order_master order = new Order_master(total_price, 1, user);
        for (Long book_id : cart_instance.keySet()) {
            Integer book_count = cart_instance.get(book_id);
            Book book = bookDao.findOne(book_id);

            BigDecimal price = book.getPrice();
            int inventory = book.getInventory();
            if (inventory - book_count >= 0) {                                      //库存还有书的情况
                BigDecimal discount = BigDecimal.valueOf(Math.random());
                bookDao.changeInventory(book.getId(), inventory - book_count);       //更新书籍库存
                total_price = total_price.add(price.multiply(new BigDecimal(book_count)));                     //计算订单总价
                Order_item local_order_item = new Order_item(book, book_count, price, discount, price.multiply(new BigDecimal(book_count)).subtract(discount), order);  //生成新的订单项
                order.getOrderItemSet().add(local_order_item);                  //绑定订单项到订单上
                System.out.println("这本书还有 " + book.getName());
            }
            else {
                System.out.println("这本书没了 " + book.getName());
                return;
            }
        }
        order.setPayment(total_price);
        System.out.println("order = " + order.toString());
        order = orderDao.save(order);
        System.out.println("下单成功");
    }

    @Override
    public List<Order_master> getOrdersByUserId(Long user_id) {
        User user = userDao.getUserById(user_id);
        return orderDao.getOrder_mastersByUserDto(user);
    }

    @Override
    public List<Order_master> queryOrders(Date startDate, Date endDate) {
        return orderDao.getOrder_mastersByDate(startDate, endDate);
    }
    @Override
    public List<Order_master> queryOrdersByUserid(Date startDate, Date endDate, Long userid) {
        User user = userDao.getUserById(userid);
        if (user == null) {
            return new ArrayList<>();
        }
        return orderDao.getOrder_mastersByDateAndUser(startDate, endDate, user);
    }
}
