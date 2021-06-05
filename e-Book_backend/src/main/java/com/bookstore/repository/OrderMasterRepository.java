package com.bookstore.repository;

import com.bookstore.entity.Order_master;
import com.bookstore.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderMasterRepository extends CrudRepository<Order_master, Long> {
    @Override
    public List<Order_master> findAll();
    public List<Order_master> getOrder_mastersByUser(User user);
    public Order_master getOrder_masterByOrderid(Long id);
    public List<Order_master> findOrder_mastersByCreatetimeBetweenOrderByCreatetimeDesc(Date startDate, Date endDate);
}
