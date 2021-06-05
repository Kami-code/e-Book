package com.bookstore.dto;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

public class UserDto {

    private Long userid;
    private int usertype;

    public UserDto() {

    }

    public UserDto (User user) {
        this.userid = user.getUserid();
        this.usertype = user.getUsertype();
    }

    public UserDto (Long userid) {
        this.userid = userid;
        this.usertype = 1;
    }

    public int getUsertype() {
        return usertype;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
