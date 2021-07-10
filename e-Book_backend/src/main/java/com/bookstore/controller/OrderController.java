package com.bookstore.controller;

import com.alibaba.fastjson.JSONArray;
import com.bookstore.entity.Order_master;
import com.bookstore.response.PurchaseResponse;
import com.bookstore.response.StaticsResponse;
import com.bookstore.service.OrderService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/purchase/all", method = RequestMethod.POST)
    public @ResponseBody
    PurchaseResponse purchaseAll(@RequestParam("cart_list") String books, @RequestParam("user_id") Long user_id) throws Exception {
        PurchaseResponse resp = new PurchaseResponse();
        Pair<Order_master, Integer> result = orderService.addOrder(user_id, books);
        if (result.getKey() == null) {
            return resp.setFail("购买失败，无可用库存！");
        }
        else {
            return resp.setSuccess(result.getKey(), result.getValue());
        }
    }

    @RequestMapping(value = "/order/{int}")
    public @ResponseBody
    List<Order_master> getOrderMasters(@PathVariable("int") Long user_id){
        return orderService.getOrdersByUserId(user_id);
    }

    @RequestMapping(value = "/order/null")
    public @ResponseBody
    JSONArray nullHandler(){
        return new JSONArray();
    }

    @RequestMapping(value = "/order/undefined")
    public @ResponseBody
    JSONArray undefinedHandler(){
        return new JSONArray();
    }

    @RequestMapping(value = "/order/{userid}", method = RequestMethod.POST)
    public @ResponseBody
    List<Order_master> getOrdersByUserIdAndTime(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @PathVariable("userid") Long userid) throws Exception {
        List<Order_master> res = orderService.queryOrdersByUserid(startDate, endDate, userid);
        return res;
    }

}
