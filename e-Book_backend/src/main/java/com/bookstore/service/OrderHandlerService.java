package com.bookstore.service;

import com.bookstore.entity.Cart;

public interface OrderHandlerService {
    void receiveMessage(Cart cart) throws Exception;
}
