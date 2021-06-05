package com.bookstore.serviceimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.UserDao;
import com.bookstore.dto.UserDto;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDto LogIn(String username, String password) {
        UserDto userDto = userDao.getUserDtoByUsernameAndPassword(username, password);
        return userDto;
    }
}
