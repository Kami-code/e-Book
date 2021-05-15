package com.bookstore.dao;

import com.bookstore.domain.Book;
import com.bookstore.domain.Order_master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMasterRepo extends CrudRepository<Order_master, Long> {
    @Override
    public List<Order_master> findAll();
    public List<Order_master> findByUserid(Long userid);
}
