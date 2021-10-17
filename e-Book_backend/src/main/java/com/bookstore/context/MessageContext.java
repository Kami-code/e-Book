package com.bookstore.context;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.User;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessageContext {
    @Autowired
    UserDao userDao;


    public ConcurrentHashMap<String, Long> simpSessionToUserID = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, Long> addToMap(String sessionId, Long userId) {
        simpSessionToUserID.put(sessionId, userId);
        System.out.println("尝试添加" + sessionId + " 当前map为：" + simpSessionToUserID);

        return simpSessionToUserID;
    }

    public ConcurrentHashMap<String, Long> deleteFromMap(String sessionId) {
        simpSessionToUserID.remove(sessionId);
        System.out.println("尝试移除" + sessionId + " 当前map为：" + simpSessionToUserID);
        return simpSessionToUserID;
    }

    public List<String> getAliveUserNames() {
        Object[] currentUserIds = simpSessionToUserID.values().toArray();
        List<String> usernames = new ArrayList<>();
        for(int i = 0; i < currentUserIds.length; i++) {
            User currentUser = userDao.getUserById((Long) currentUserIds[i]);
            usernames.add(currentUser.getUsername());
        }
        return usernames;
    }
}
