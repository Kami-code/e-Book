package com.bookstore.webservice;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws22.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfWebServiceConfig {

    // 这里需要注意 若beanName命名不是 cxfServletRegistration 时，会创建两个CXFServlet的。
    // 具体可查看下自动配置类：Declaration
    // org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration
    // 也可以不设置此bean 直接通过配置项 cxf.path 来修改访问路径的
    @Bean("cxfServletRegistration")
    public ServletRegistrationBean<CXFServlet> cxfServletRegistration() {
        // 注册servlet 拦截/ws 开头的请求 不设置 默认为：/services/*
        return new ServletRegistrationBean<CXFServlet>(new CXFServlet(), "/ws/*");
    }

    /**
     * 申明业务处理类 当然也可以直接 在实现类上标注 @Service
     *
     */
    @Bean
    public SearchWebServiceImpl searchService() {
        return new SearchWebServiceImpl();
    }

    /*
     * 非必要项
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        return springBus;
    }

    /*
     * 发布endpoint
     */
    @Bean
    public Endpoint endpoint(SearchWebService searchWebService) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), searchWebService);
        endpoint.publish("/searchByDescription");// 发布地址
        endpoint.getInInterceptors().add(new TestInterceptor());
        return endpoint;
    }
}
