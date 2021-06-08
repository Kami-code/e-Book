package com.bookstore.repository;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> getUsersByUsername(String n);
    public User getUserByUsernameAndPassword(String username, String password);
    public User getUserByUserid(Long n);
    public User getUserByUsername(String n);
    @Override
    public List<User> findAll();
}
