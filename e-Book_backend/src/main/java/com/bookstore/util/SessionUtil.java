package com.bookstore.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtil {


    public static HttpSession getSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request.getSession();
    }

    public static HttpSession setSession(String key, Object object) {
        HttpSession httpSession = getSession();
        httpSession.setAttribute(key, object);
        return httpSession;
    }

    public static boolean removeSession() {
        HttpSession httpSession = getSession();
        httpSession.invalidate();
        return true;
    }

}
