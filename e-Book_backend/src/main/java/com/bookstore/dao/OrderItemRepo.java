package com.bookstore.dao;

import com.bookstore.domain.Order_item;
import com.bookstore.domain.Order_master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends CrudRepository<Order_item, Long> {
    @Override
    public List<Order_item> findAll();
    public List<Order_item> findByOrderid(Long order_id);
}
