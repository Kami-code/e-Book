//package com.bookstore.receiver;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.bookstore.dao.BookDao;
//import com.bookstore.dao.OrderDao;
//import com.bookstore.dao.UserDao;
//import com.bookstore.entity.*;
//import javafx.util.Pair;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class Order {
//    @Autowired
//    UserDao userDao;
//    @Autowired
//    BookDao bookDao;
//    @Autowired
//    OrderDao orderDao;
//
//    @JmsListener(destination = "order")//, containerFactory = "myFactory")
//    public void receiveMessage(Cart cart) {
//        System.out.println("Received <" + cart.toString() + ">");
//        Long user_id = cart.getUser_id();
//        User user = userDao.getUserById(user_id);
//
//
//        Map<Long, Integer> cart_instance = cart.getInstance();
//        BigDecimal total_price = new BigDecimal(0);
//        Order_master order = new Order_master(total_price, 1, user);
//        for (Long book_id : cart_instance.keySet()) {
//            Integer book_count = cart_instance.get(book_id);
//            Book book = bookDao.findOne(book_id);
//
//            BigDecimal price = book.getPrice();
//            int inventory = book.getInventory();
//            if (inventory - book_count >= 0) {                                      //库存还有书的情况
//                BigDecimal discount = BigDecimal.valueOf(Math.random());
//                bookDao.changeInventory(book.getId(), inventory - book_count);       //更新书籍库存
//                total_price = total_price.add(price.multiply(new BigDecimal(book_count)));                     //计算订单总价
//                Order_item local_order_item = new Order_item(book, book_count, price, discount, price.multiply(new BigDecimal(book_count)).subtract(discount), order);  //生成新的订单项
//                order.getOrderItemSet().add(local_order_item);                  //绑定订单项到订单上
//                System.out.println("这本书还有 " + book.getName());
//            }
//            else {
//                System.out.println("这本书没了 " + book.getName());
//                return;
//            }
//        }
//        order.setPayment(total_price);
//        System.out.println("order = " + order.toString());
//        order = orderDao.save(order);
//        System.out.println("下单成功");
//    }
//}
