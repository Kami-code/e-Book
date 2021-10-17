package com.bookstore.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.context.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MessageController {
    //spring提供的发送消息模板
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    MessageContext messageContext;


    @Scheduled(fixedRate = 1000)
    public void callback() {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/user", "当前用户: " + messageContext.getAliveUserNames());

    }

    @MessageMapping("/top")
    @SendTo("/topic/greetings")
    public String greeting(String message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        System.out.println(message);
        JSONObject jsonObject = JSON.parseObject(message);
        String username = jsonObject.getString("username");
        String word = jsonObject.getString("word");
        return message.toString();
    }
}
