package com.bookstore.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.UserDao;
import com.bookstore.dto.UserDto;
import com.bookstore.entity.Book;
import com.bookstore.entity.Order_item;
import com.bookstore.entity.Order_master;
import com.bookstore.entity.User;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Order_master addOrder(Long user_id, String books) {
        User user = userDao.getUserById(user_id);
        JSONArray cart = JSON.parseArray(books);    //购物车
        int length = cart.size();
        BigDecimal total_price = new BigDecimal(0);
        Order_master order = new Order_master(total_price, 1, user);
        for (int i = 0; i < length; ++i) {
            JSONObject json_book = cart.getJSONObject(i);
            BigDecimal price = BigDecimal.valueOf(json_book.getDouble("price"));
            int number = json_book.getInteger("number");                    //购买的数量
            Book book = bookDao.findOne(json_book.getLong("id"));
            int inventory = book.getInventory();
            if (inventory - number >= 0) {                                      //库存还有书的情况
                BigDecimal discount = BigDecimal.valueOf(Math.random());
                bookDao.changeInventory(book.getId(), inventory - number);       //更新书籍库存
                total_price = total_price.add(price.multiply(new BigDecimal(number)).subtract(discount));                     //计算订单总价
                Order_item local_order_item = new Order_item(book, number, price, discount, price.multiply(new BigDecimal(number)).subtract(discount), order);  //生成新的订单项
                order.getOrderItemSet().add(local_order_item);                  //绑定订单项到订单上
            }
        }
        order.setPayment(total_price);
        return orderDao.save(order);
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
