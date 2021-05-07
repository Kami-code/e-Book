package com.bookstore.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Table(name="USER_AUTH")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long user_id;
    private String username;
    private String password;
    private int user_type;


    public User(String a, String p, int t) {
        username = a;
        password = p;
        user_type = t;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String a) {username = a;}
    public String getPassword() {
        return password;
    }
    public void setPassword(String a) {password = a;}
    public int getType() {
        return user_type;
    }
    public void setType(int a) {user_type = a;}
    public Long getUser_id() {return user_id;}
    public void setUser_id(Long id) {this.user_id = id;}
}

