package com.bookstore.dao;

import com.bookstore.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    public List<User> findByUsername(String n);
    public List<User> findByUsernameAndPassword(String username, String password);
}
