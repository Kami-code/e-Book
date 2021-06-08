package com.bookstore.dto;

import com.bookstore.entity.User;

public class UserDto {

    private Long userid;
    private String username;
    private String telnum;
    private String email;
    private int usertype;
    private int isBlocked;

    public UserDto() {

    }

    public UserDto (User user) {
        this.userid = user.getUserid();
        this.usertype = user.getUsertype();
        this.isBlocked = user.getIsBlocked();
        this.username = user.getUsername();
        this.telnum = user.getTelnum();
        this.email = user.getEmail();
    }

    public UserDto (Long userid) {
        this.userid = userid;
        this.usertype = 1;
        this.isBlocked = 0;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }
    public int getIsBlocked() {
        return isBlocked;
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
    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelnum() {
        return telnum;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
