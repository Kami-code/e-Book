package com.bookstore.interceptor;

import com.bookstore.context.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.messaging.simp.stomp.StompCommand.CONNECT;

/**
 * <websocke消息监听，用于监听websocket用户连接情况>
 * <功能详细描述>
 **/
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    @Autowired
    MessageContext messageContext;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel messageChannel) {
        System.out.println("message"+ message);
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        /*
         * 拿到消息头对象后，我们可以做一系列业务操作
         * 1. 通过getSessionAttributes()方法获取到websocketSession，
         *    就可以取到我们在WebSocketHandshakeInterceptor拦截器中存在session中的信息
         * 2. 我们也可以获取到当前连接的状态，做一些统计，例如统计在线人数，或者缓存在线人数对应的令牌，方便后续业务调用
         */

        // 这里只是单纯的打印，可以根据项目的实际情况做业务处理

        // 根据连接状态做处理，这里也只是打印了下，可以根据实际场景，对上线，下线，首次成功连接做处理
        String simpSessionId = (String) message.getHeaders().get("simpSessionId");


        switch (accessor.getCommand())
        {
            // 首次连接
            case CONNECT:
                Map<String, Object> nativeHeaders = (Map<String, Object>) message.getHeaders().get("nativeHeaders");
                ArrayList<Object> arrayList = (ArrayList<Object>) nativeHeaders.get("userid");
                String user_id = (String) arrayList.get(0);

                System.out.println("user_id = " + user_id);
                if ("null".equals(user_id)) {
                    System.out.println("未授权访问，拦截！");
                    return null;
                }
                else if  (!messageContext.simpSessionToUserID.containsValue(Long.parseLong(user_id))) {
                    messageContext.addToMap(simpSessionId, Long.parseLong(user_id));
                }
                break;
            // 连接中
            case CONNECTED:
                break;
            // 下线
            case DISCONNECT:
                messageContext.deleteFromMap(simpSessionId);
                break;
            default:
                break;
        }
        return message;
    }

}
