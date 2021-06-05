package com.bookstore.service;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.Order_master;

public interface UserService {

    UserDto LogIn(String username, String password);

}
