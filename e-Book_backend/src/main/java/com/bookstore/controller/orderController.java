package com.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.dao.BookRepo;
import com.bookstore.dao.OrderItemRepo;
import com.bookstore.dao.OrderMasterRepo;
import com.bookstore.dao.UserRepo;
import com.bookstore.domain.Book;
import com.bookstore.domain.Order_item;
import com.bookstore.domain.Order_master;
import com.bookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class orderController {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private OrderMasterRepo orderMasterRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;

    @RequestMapping(value = "/purchase/all", method = RequestMethod.POST)
    public @ResponseBody
    JSONArray purchaseAll(@RequestParam("cart_list") String books, @RequestParam("user_id") Long user_id) throws Exception {
        System.out.println(books);
        JSONArray cart = JSON.parseArray(books);
        JSONArray result = new JSONArray();
        int length = cart.size();
        double total_price = 0;
        Order_master order = new Order_master(total_price, 1, user_id);
        order = orderMasterRepo.save(order);
        for (int i = 0; i < length; ++i) {
            JSONObject book = cart.getJSONObject(i);
            Long id = book.getLong("id");
            double price = book.getDouble("price");
            int number = book.getInteger("number"); //购买的数量
            Book local_book = bookRepo.getBookById(id);
            int inventory = local_book.getInventory();
            if (inventory - number >= 0) {
                local_book.setInventory(inventory - number);
                bookRepo.save(local_book);
                total_price += (price * number);
                Order_item local_order_item = new Order_item(local_book.getId(),
                        number, price, 0, price * number, order.getOrderid());
                orderItemRepo.save(local_order_item);
            }
            else {
                result.add(book);
            }
        }

        order.setPayment(total_price);
        orderMasterRepo.save(order);
        System.out.println(total_price);
        return result;
    }

    @RequestMapping(value = "/order/{int}")
    public @ResponseBody
    JSONArray getOrderMasters(@PathVariable("int") Long user_id){
        List<Order_master> order_masters = orderMasterRepo.findByUserid(user_id);
        JSONArray result = new JSONArray();
        for (int i = 0; i < order_masters.size(); ++i) {
            JSONArray local_result = new JSONArray();
            List<Order_item> order_items = orderItemRepo.findByOrderid(order_masters.get(i).getOrderid());
            local_result.add(order_items);
            JSONObject local_object = new JSONObject();
            local_object.put("order_master", order_masters.get(i));
            local_object.put("order_item", local_result);
            result.add(local_object);
        }
        return result;
    }

//    @RequestMapping(value = "/order_item/{int}")
//    public @ResponseBody
//    List<Order_item> getOrderItems(@PathVariable("int") Long order_id){
//
//
//        System.out.println(order_id);
//        return order_items;
//    }
}
