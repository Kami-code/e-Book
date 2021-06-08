package com.bookstore.service;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.Order_master;

import java.util.List;

public interface UserService {

    UserDto LogIn(String username, String password) throws Exception;
    UserDto SignUp(String username, String password, String telnum, String email) throws Exception;
    List<UserDto> GetAll () throws Exception;
    UserDto ChangeBlockedStatus(Long userid, int status) throws Exception;
}
