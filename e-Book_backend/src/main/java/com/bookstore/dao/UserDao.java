package com.bookstore.dao;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDao {
    UserDto getUserDtoById(Long id);
    User getUserById(Long id);
    UserDto getUserDtoByUsernameAndPassword(String username, String password);
    UserDto getUserDtoByUsername(String username);
    UserDto saveByUser(User user);
    List<User> getAllUsers();
    List<UserDto> getAllUserDtos();
    UserDto setBlockedStatus(Long userid, int status);
    Page<User> getUserByPage(int pageIndex, int pageSize);
    Long count();
}
