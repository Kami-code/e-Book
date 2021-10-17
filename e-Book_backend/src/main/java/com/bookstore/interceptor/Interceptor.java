package com.bookstore.interceptor;

import com.bookstore.counter.AtomicCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//spring boot入门（七） springboot的拦截器Interceptor。最完整、简单易懂、详细的spring boot教程。
//https://blog.csdn.net/m0_38075425/article/details/81123689
public class Interceptor implements HandlerInterceptor {
    @Autowired
    AtomicCounter ac;

    public void afterCompletion(HttpServletRequest req, HttpServletRequest res, Object obj, Exception e) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {

        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, Set-Cookie");
        res.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        try {
            //每一个session理论上只能记一次访问，因此在session里面存一个访问标记，如果存在标记，则不再计算此次访问
            synchronized(this) { //同步锁
            if(req.getSession().getAttribute("accessedFlag") == null) {

                    ac.increment();
                    req.getSession().setAttribute("accessedFlag", true);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }
}
