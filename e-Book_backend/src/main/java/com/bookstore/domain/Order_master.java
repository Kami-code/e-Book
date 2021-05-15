package com.bookstore.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Table(name = "order_master")
@Entity
public class Order_master {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="order_id")
    private Long orderid;
    private double payment;
    private int method;


    @JoinTable(name = "user_auth", joinColumns = { @JoinColumn(name = "user_id") })
    @Column(name="user_id")
    private Long userid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createtime;


    public Order_master(double p, int m, Long u) {
        payment = p;
        method = m;
        userid = u;
    }

    public Order_master() {

    }

    public double getPayment() {
        return payment;
    }
    public int getMethod() {
        return method;
    }
    public void setMethod(int method) {
        this.method = method;
    }
    public void setPayment(double payment) {
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
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public Long getUserid() {
        return userid;
    }
    public Date getCreatetime() {
        return createtime;
    }
}
