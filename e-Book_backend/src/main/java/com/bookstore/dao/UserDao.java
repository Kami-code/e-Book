package com.bookstore.dao;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.User;

public interface UserDao {
    UserDto getUserDtoById(Long id);
    User getUserById(Long id);
    UserDto getUserDtoByUsernameAndPassword(String username, String password);
}
