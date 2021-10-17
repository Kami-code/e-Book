package com.bookstore.webservice;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class TestInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    public TestInterceptor() {
        super(Phase.RECEIVE);//父类没有无参构造函数，定义拦截阶段，这里意思为收到数据即执行
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        System.out.println(message);
        HttpServletResponse response = (HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);
        response.setHeader("Access-Control-Allow-Origin", "*");
        if ("OPTIONS".equals(message.get(SoapMessage.HTTP_REQUEST_METHOD))) {
            response.setHeader("Access-Control-Allow-Headers",
                    ((HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST))
                            .getHeader("Access-Control-Request-Headers"));
            message.getInterceptorChain().doInterceptStartingAfter(message, null);
        }
    }
}
