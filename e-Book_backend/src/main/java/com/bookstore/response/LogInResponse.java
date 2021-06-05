package com.bookstore.response;

import com.bookstore.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

public class LogInResponse {
    private Map<String, Object> info = null;

    public Map<String, Object> getInfo() {
        return info;
    }
    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public LogInResponse() {
        info = new HashMap<>();
    }

    public LogInResponse setSuccess(UserDto userDto) {
        info.put("result", "success");
        info.put("user", userDto);
        return this;
    }

    public LogInResponse setFail() {
        info.put("result", "fail");
        return this;
    }
}
