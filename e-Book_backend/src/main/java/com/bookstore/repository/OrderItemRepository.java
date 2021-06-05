package com.bookstore.repository;

import com.bookstore.entity.Order_item;
import com.bookstore.entity.Order_master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<Order_item, Long> {
    @Override
    public List<Order_item> findAll();
    public List<Order_item> findByOrderMaster(Order_master orderMaster);
}
