package com.bookstore.response;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.Order_master;

import java.util.HashMap;
import java.util.Map;

public class PurchaseResponse{
    private Map<String, Object> info = null;

    public Map<String, Object> getInfo() {
        return info;
    }
    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public PurchaseResponse() {
        info = new HashMap<>();
    }

    public PurchaseResponse setSuccess(Order_master orderMaster, Integer fail_books) {
        info.put("result", "success");
        info.put("success_books", orderMaster.getOrderItemSet().size());
        info.put("fail_books", fail_books);
        return this;
    }

    public PurchaseResponse setFail(String message) {
        info.put("result", "fail");
        info.put("message", message);
        return this;
    }
}
