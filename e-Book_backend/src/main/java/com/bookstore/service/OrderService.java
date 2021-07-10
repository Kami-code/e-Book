package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Order_master;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Pair<Order_master, Integer> addOrder(Long user_id, String books);
    List<Order_master> getOrdersByUserId(Long user_id);
    List<Order_master> queryOrders(Date startDate, Date endDate);
    List<Order_master> queryOrdersByUserid(Date startDate, Date endDate, Long userid);
}
