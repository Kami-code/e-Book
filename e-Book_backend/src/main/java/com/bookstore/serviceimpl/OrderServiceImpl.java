package com.bookstore.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.UserDao;
import com.bookstore.dto.UserDto;
import com.bookstore.entity.*;
import com.bookstore.service.OrderService;
import com.bookstore.util.SessionUtil;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    WebApplicationContext applicationContext;

    @Override
    public boolean placeOrder() {
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        HttpSession session = SessionUtil.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) { return false; }
        jmsTemplate.convertAndSend("order", cart);
        session.removeAttribute("cart");
        return true;
    }

    @Override
    public List<Order_master> getOrdersByUserId(Long user_id) {
        User user = userDao.getUserById(user_id);
        return orderDao.getOrder_mastersByUserDto(user);
    }

    @Override
    public List<Order_master> queryOrders(Date startDate, Date endDate) {
        return orderDao.getOrder_mastersByDate(startDate, endDate);
    }
    @Override
    public List<Order_master> queryOrdersByUserid(Date startDate, Date endDate, Long userid) {
        User user = userDao.getUserById(userid);
        if (user == null) {
            return new ArrayList<>();
        }
        return orderDao.getOrder_mastersByDateAndUser(startDate, endDate, user);
    }
}
