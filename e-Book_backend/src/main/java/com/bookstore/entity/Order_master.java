package com.bookstore.entity;

import com.bookstore.dto.UserDto;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Table(name = "order_master")
@Entity
public class Order_master implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="order_master_id")
    private Long orderid;
    private BigDecimal payment;
    private int method;

    @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "orderMaster", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order_item> orderItemSet = new ArrayList<>();;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createtime;

    public Order_master() {
    }

    public Order_master(BigDecimal payment, int method, User user) {
        this.payment = payment;
        this.method  = method;
        this.user = user;
    }
    public BigDecimal getPayment() {
        return payment;
    }
    public int getMethod() {
        return method;
    }
    public void setMethod(int method) {
        this.method = method;
    }
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
    public Long getOrderid() {
        return orderid;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public void setOrderItemSet(List<Order_item> orderItemSet) {
        this.orderItemSet = orderItemSet;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public List<Order_item> getOrderItemSet() {
        return orderItemSet;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
