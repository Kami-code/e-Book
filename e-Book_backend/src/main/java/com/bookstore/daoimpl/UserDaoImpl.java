package com.bookstore.daoimpl;

import com.bookstore.dao.UserDao;
import com.bookstore.dto.UserDto;
import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUserDtoById(Long id) {
        return userRepository.getUserByUserid(id).toUserDto();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
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
    @Override
    public UserDto getUserDtoByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        return user.toUserDto();
    }
    @Override
    public UserDto saveByUser(User user) {
        return userRepository.save(user).toUserDto();
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public List<UserDto> getAllUserDtos() {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); ++i) {
            userDtos.add(users.get(i).toUserDto());
        }
        return userDtos;
    }

    @Override
    public UserDto setBlockedStatus(Long userid, int status) {
        User user = userRepository.getUserByUserid(userid);
        if (user == null) {
            return null;
        }
        user.setIsBlocked(status);
        return userRepository.save(user).toUserDto();
    }

    @Override
    public Page<User> getUserByPage(int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}
