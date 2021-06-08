package com.bookstore.daoimpl;

import com.bookstore.dao.OrderDao;
import com.bookstore.dto.UserDto;
import com.bookstore.entity.Order_master;
import com.bookstore.entity.User;
import com.bookstore.repository.OrderMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    public Order_master save(Order_master orderMaster) { return orderMasterRepository.save(orderMaster); }
    @Override
    public List<Order_master> getOrder_mastersByUserDto(User user) {
        return orderMasterRepository.getOrder_mastersByUser(user);
    }
    @Override
    public List<Order_master> getOrder_mastersByDate(Date startDate, Date endDate) {
        return orderMasterRepository.findOrder_mastersByCreatetimeBetweenOrderByCreatetimeDesc(startDate, endDate);
    }
    @Override
    public List<Order_master> getOrder_mastersByDateAndUser(Date startDate, Date endDate, User user) {
        return orderMasterRepository.findOrder_mastersByUserAndCreatetimeBetweenOrderByCreatetimeDesc(user, startDate, endDate);
    }
}
