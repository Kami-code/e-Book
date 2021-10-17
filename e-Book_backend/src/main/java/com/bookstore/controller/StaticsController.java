package com.bookstore.controller;

import com.bookstore.counter.AtomicCounter;
import com.bookstore.entity.Order_master;
import com.bookstore.response.StaticsResponse;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StaticsController {
    @Autowired
    OrderService orderService;
    @Autowired
    AtomicCounter ac;


    @RequestMapping(value = "/statics/order", method = RequestMethod.POST)
    public @ResponseBody
    List<Map<String, Object>> queryOrders(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) throws Exception {
        StaticsResponse resp = new StaticsResponse();
        List<Order_master> res = orderService.queryOrders(startDate, endDate);
        resp.setOrderResponse(res);
        return resp.getOrder_info();
    }

    @RequestMapping(value = "/statics/book", method = RequestMethod.POST)
    public @ResponseBody
    List<Map<String, Object>> queryBooks(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) throws Exception {
        StaticsResponse resp = new StaticsResponse();
        List<Order_master> res = orderService.queryOrders(startDate, endDate);
        resp.setBookResponse(res);
        return resp.getBook_info();
    }

    @RequestMapping(value = "/statics/user", method = RequestMethod.GET)
    public @ResponseBody
    int getBrowserCount() throws Exception {
        return ac.value();
    }
}
