package com.bookstore.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//spring boot入门（七） springboot的拦截器Interceptor。最完整、简单易懂、详细的spring boot教程。
//https://blog.csdn.net/m0_38075425/article/details/81123689
public class Interceptor implements HandlerInterceptor {
    public void afterCompletion(HttpServletRequest req, HttpServletRequest res, Object obj, Exception e) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, Set-Cookie");
        res.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        return true;
    }
}
