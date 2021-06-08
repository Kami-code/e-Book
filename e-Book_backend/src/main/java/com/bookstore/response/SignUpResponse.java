package com.bookstore.response;

import com.bookstore.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

public class SignUpResponse {
    private Map<String, Object> info = null;

    public Map<String, Object> getInfo() {
        return info;
    }
    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public SignUpResponse() {
        info = new HashMap<>();
    }

    public SignUpResponse setSuccess(UserDto userDto) {
        info.put("result", "success");
        info.put("user", userDto);
        return this;
    }

    public SignUpResponse setFail(String message) {
        info.put("result", "fail");
        info.put("message", message);
        return this;
    }
}
