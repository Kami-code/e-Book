package com.bookstore.entity;

import com.bookstore.dto.UserDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="USER_AUTH")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
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

    public User(String username, String password, String email, String telnum, int usertype, int isBlocked) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.telnum = telnum;
        this.usertype = usertype;
        this.isBlocked = isBlocked;
    }

    public String getUsername() {return username;}

    public Long getUserid() {
        return userid;
    }

    public int getUsertype() {
        return usertype;
    }

    public int getIsBlocked() {
        return isBlocked;
    }

    public String getTelnum() {
        return telnum;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public UserDto toUserDto() {
        return new UserDto(this);
    }
}

