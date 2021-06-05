package com.bookstore.entity;

import com.bookstore.dto.UserDto;

import javax.persistence.*;

@Table(name="USER_AUTH")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name= "user_id")
    private Long userid;
    private String username;
    private String password;
    @Column(name="user_type")
    private int usertype;

    public User() {

    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public int getUsertype() {
        return usertype;
    }
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    public UserDto toUserDto() { return new UserDto(this); }
}

