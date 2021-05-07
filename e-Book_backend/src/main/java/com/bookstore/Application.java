package com.bookstore;

import com.bookstore.dao.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration // 作用: 开启自动配置 初始化spring环境 springmvc环境
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
@EnableWebMvc
@ComponentScan // 作用: 用来扫描相关注解 扫描范围 当前入口类所在的包及子包(com.bookstore及其子包)
public class Application {
    public static void main(String[] args) {
        // springApplication: spring应用类    作用: 用来启动springboot应用
        // 参数1: 传入入口类 类对象   参数2: main函数的参数
        SpringApplication.run(Application.class, args);
    }
}
