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

    public UserDto toUserDto() {
        return new UserDto(this);
    }
}

