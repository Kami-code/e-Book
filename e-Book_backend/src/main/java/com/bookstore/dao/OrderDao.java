package com.bookstore.dao;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.Book;
import com.bookstore.entity.Order_item;
import com.bookstore.entity.Order_master;
import com.bookstore.entity.User;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;

public interface OrderDao {

    Order_master save(Order_master orderMaster);
    List<Order_master> getOrder_mastersByUserDto(User user);
    List<Order_master> getOrder_mastersByDate(Date startDate, Date endDate);
}
