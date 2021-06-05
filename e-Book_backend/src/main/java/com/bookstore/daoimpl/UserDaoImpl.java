package com.bookstore.daoimpl;

import com.bookstore.dao.UserDao;
import com.bookstore.dto.UserDto;
import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUserDtoById(Long id) {
        return userRepository.getUserByUserid(id).toUserDto();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserByUserid(id);
    }
    @Override
    public UserDto getUserDtoByUsernameAndPassword(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        if (user == null) {
            return null;
        }
        return user.toUserDto();
    }
}
