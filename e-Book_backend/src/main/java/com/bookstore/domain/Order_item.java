package com.bookstore.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.websocket.ClientEndpoint;
import java.util.Date;

@Table(name = "order_item")
@Entity
public class Order_item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_item_id")
    private Long orderitemid;
    @Column(name  = "item_cnt")
    private int itemcnt;
    @Column(name = "item_price")
    private double itemprice;
    @Column(name = "discount_money")
    private double discountmoney;
    @Column(name = "total_money")
    private double totalmoney;

    @JoinTable(name = "order_master", joinColumns = { @JoinColumn(name = "order_id") })
    @Column(name = "order_id")
    private Long orderid;
    @JoinTable(name = "book", joinColumns = { @JoinColumn(name = "id") })
    @Column(name = "item_id")
    private Long itemid;


    public Order_item(Long i, int c, double p, double d, double t, Long o) {
        itemid = i;
        itemcnt = c;
        itemprice = p;
        discountmoney = d;
        totalmoney = t;
        orderid = o;
    }

    public Order_item() {

    }
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
    public Long getOrderid() {
        return orderid;
    }
    public void setDiscountmoney(double discountmoney) {
        this.discountmoney = discountmoney;
    }
    public void setItemcnt(int itemcnt) {
        this.itemcnt = itemcnt;
    }
    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }
    public void setItemprice(double itemprice) {
        this.itemprice = itemprice;
    }
    public void setOrderitemid(Long orderitemid) {
        this.orderitemid = orderitemid;
    }
    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }
    public double getDiscountmoney() {
        return discountmoney;
    }
    public double getItemprice() {
        return itemprice;
    }
    public int getItemcnt() {
        return itemcnt;
    }
    public Long getItemid() {
        return itemid;
    }
    public double getTotalmoney() {
        return totalmoney;
    }
    public Long getOrderitemid() {
        return orderitemid;
    }
}
