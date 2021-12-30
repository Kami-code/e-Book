package com.bookstore.controller;

import com.alibaba.fastjson.JSONArray;
import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.entity.Order_master;
import com.bookstore.response.PurchaseResponse;
import com.bookstore.service.CartService;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public @ResponseBody
    PurchaseResponse placeOrder() throws Exception {
        PurchaseResponse resp = new PurchaseResponse();
        //Pair<Order_master, Integer> result = orderService.addOrder(user_id, books);
        orderService.placeOrder();
        return resp;
//        if (result.getKey() == null) {
//            return resp.setFail("购买失败，无可用库存！");
//        }
//        else {
//            return resp.setSuccess(result.getKey(), result.getValue());
//        }
    }

//    @RequestMapping(value = "/car")
//    public @ResponseBody List<Book> addBookToCar(@PathVariable("bookid") Long book_id) throws Exception {
//        return bookService.getBooks();
//    }
    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public @ResponseBody
    Cart addBookToCar(@RequestParam("book_id") Long book_id) throws Exception {
        return cartService.addBooksToCartSession(book_id);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public @ResponseBody
    Cart getCart() throws Exception {
        return cartService.getCart();
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
