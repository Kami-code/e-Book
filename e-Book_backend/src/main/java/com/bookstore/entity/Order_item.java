package com.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private BigDecimal itemprice;
    @Column(name = "discount_money")
    private BigDecimal discountmoney;
    @Column(name = "total_money")
    private BigDecimal totalmoney;

    @OneToOne(targetEntity = Book.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id",referencedColumnName = "id")
    @NotFound(action= NotFoundAction.IGNORE)
    private Book book;

//    @ManyToOne(targetEntity = Order_master.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "order_id",referencedColumnName = "order_id")

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="order_id")
    private Order_master orderMaster;

    public Order_item(Book b, int c, BigDecimal p, BigDecimal d, BigDecimal t, Order_master o) {
        book = b;
        itemcnt = c;
        itemprice = p;
        discountmoney = d;
        totalmoney = t;
        orderMaster = o;
    }

    public Order_item() {

    }

    public Long getOrderitemid() {
        return orderitemid;
    }
    public void setOrderitemid(Long orderitemid) {
        this.orderitemid = orderitemid;
    }
    public void setDiscountmoney(BigDecimal discountmoney) {
        this.discountmoney = discountmoney;
    }
    public void setItemcnt(int itemcnt) {
        this.itemcnt = itemcnt;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public void setItemprice(BigDecimal itemprice) {
        this.itemprice = itemprice;
    }
    @JsonBackReference
    public void setOrderMaster(Order_master orderMaster) {
        this.orderMaster = orderMaster;
    }
    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }
    public BigDecimal getDiscountmoney() {
        return discountmoney;
    }
    public BigDecimal getItemprice() {
        return itemprice;
    }
    public int getItemcnt() {
        return itemcnt;
    }
    public Book getBook() {
        return book;
    }
    public BigDecimal getTotalmoney() {
        return totalmoney;
    }
    @JsonBackReference
    public Order_master getOrderMaster() {
        return orderMaster;
    }
}
