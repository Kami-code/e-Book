package com.bookstore.controller;

import com.bookstore.counter.AtomicCounter;
import com.bookstore.entity.Order_master;
import com.bookstore.response.StaticsResponse;
import com.bookstore.service.OrderService;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

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
/*
@SpringBootApplication
public class MessagingRedisApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingRedisApplication.class);
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {  return new MessageListenerAdapter(receiver, "receiveMessage"); }
    @Bean
    Receiver receiver() { return new Receiver(); }
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(MessagingRedisApplication.class, args);
    }
}

public class EchoEndpoint extends Endpoint {
    @Override
    public void onOpen(final Session session, EndpointConfig config) {
        session.addMessageHandler(
                new MessageHandler.Whole<String>() {
                    @Override
                    public void onMessage(String msg) {
                        try {
                            session.getBasicRemote().sendText(msg);
                        } catch (IOException e) {

                        }
                    }
                });
    }
}
*/
