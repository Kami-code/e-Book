package com.bookstore.queue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import com.bookstore.vo.AsyncVo;

/**
 * 存放所有异步处理接口请求队列的对象,一个接口对应一个队列
 *
 * @author Logen
 *
 */
@Component
public class RequestQueue {

    /**
     * 处理下订单接口的队列，设置缓冲容量为50
     */
    private BlockingQueue<AsyncVo<String, Object>> orderQueue = new LinkedBlockingQueue<>(50);

    public BlockingQueue<AsyncVo<String, Object>> getOrderQueue() {
        return orderQueue;
    }

}
