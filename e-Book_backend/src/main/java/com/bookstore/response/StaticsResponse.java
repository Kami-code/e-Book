package com.bookstore.response;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.Order_master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticsResponse {

    private List<Map<String, Object>> order_info = null;
    private List<Map<String, Object>> book_info = null;

    public List<Map<String, Object>> getBook_info() {
        return book_info;
    }
    public void setBook_info(List<Map<String, Object>> book_info) {
        this.book_info = book_info;
    }
    public List<Map<String, Object>> getOrder_info() {
        return order_info;
    }
    public void setOrder_info(List<Map<String, Object>> order_info) {
        this.order_info = order_info;
    }

    public StaticsResponse() {
        order_info = new ArrayList<Map<String, Object>>();
        book_info = new ArrayList<Map<String, Object>>();
    }

    public StaticsResponse setOrderResponse(List<Order_master> orders) {
        for (int i = 0; i < orders.size(); i++) {
            Map<String, Object> local_info = new HashMap<>();
            local_info.put("createtime", orders.get(i).getCreatetime());
            local_info.put("username", orders.get(i).getUser().getUsername());
            local_info.put("payment", orders.get(i).getPayment());
            order_info.add(local_info);
        }
        return this;
    }

    public StaticsResponse setBookResponse(List<Order_master> orders) {

        for (int i = 0; i < orders.size(); i++) {

            for (int j = 0; j < orders.get(i).getOrderItemSet().size(); j++) {
                Map<String, Object> local_info = new HashMap<>();
                local_info.put("createtime", orders.get(i).getCreatetime());
                local_info.put("book", orders.get(i).getOrderItemSet().get(j).getBook().getName());
                local_info.put("count", orders.get(i).getOrderItemSet().get(j).getItemcnt());
                book_info.add(local_info);
//                System.out.println(local_info);
            }

        }
        return this;
    }

}
