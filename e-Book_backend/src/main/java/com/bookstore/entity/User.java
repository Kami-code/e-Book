package com.bookstore.entity;

import com.bookstore.dto.UserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="USER_AUTH")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name= "user_id")
    private Long userid;
    private String username;
    private String password;
    private String email;
    private String telnum;
    @Column(name= "is_blocked")
    private int isBlocked;
    @Column(name="user_type")
    private int usertype;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order_master> orderMasterList = new ArrayList<>();;

    public User() {

    }

    public User(String username, String password, String email, String telnum, int usertype, int isBlocked) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.telnum = telnum;
        this.usertype = usertype;
        this.isBlocked = isBlocked;
    }

    public List<Order_master> getOrderMasterList() {
        return orderMasterList;
    }
    public void setOrderMasterList(List<Order_master> orderMasterList) {
        this.orderMasterList = orderMasterList;
    }
    public int getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
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
    public String getEmail() {
        return email;
    }
    public String getTelnum() {
        return telnum;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }
}

