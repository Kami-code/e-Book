package com.bookstore;

import com.bookstore.entity.Book;

import com.bookstore.node.BookNode;
import com.bookstore.node.TagNode;
import com.bookstore.repository.BookNodeRepository;
import com.bookstore.repository.TagNodeRepository;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.jms.support.converter.MessageConverter;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@EnableNeo4jRepositories
//@EnableAutoConfiguration // ??????: ?????????????????? ?????????spring?????? springmvc??????
//@ComponentScan // ??????: ???????????????????????? ???????????? ????????????????????????????????????(com.bookstore????????????)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableWebMvc
@EnableJms

@EnableScheduling
@EnableCaching
public class Application {
    @Bean
    public JmsListenerContainerFactory<?> myFactory(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

   @Bean
    CommandLineRunner demo(BookNodeRepository bookNodeRepository, TagNodeRepository tagNodeRepository, BookRepository bookRepository) {
        //???????????????????????????????????????return??????neo4j
        return args -> {return;};

        //??????????????????????????????????????????
//        return args -> {
//            List<String> types = new ArrayList<>();
//            types.add("??????");
//            types.add("??????");
//            types.add("???????????????");
//            types.add("????????????");
//            types.add("????????????");
//            types.add("??????/????????????");
//            types.add("????????????");
//            types.add("????????????");
//            types.add("????????????");
//            types.add("????????????");
//            bookNodeRepository.deleteAll();
//            tagNodeRepository.deleteAll();
//            List<Book> books = bookRepository.findAll();
//            for (Book book: books) {
//                BookNode bookNode = new BookNode(book.getName());
//                TagNode tagNode = tagNodeRepository.findByName(book.getType());
//                if (tagNode == null) {
//                    tagNode = new TagNode(book.getType());
//                }
//                bookNode.containTag(tagNode);
//                tagNodeRepository.save(tagNode);
//                while (true) {
//                    int num = (int) (Math.random() * types.size());
//                    String random_type = types.get(num);
//                    if (random_type.equals(book.getType()) == true) {
//                        continue;
//                    }
//                    TagNode random_tag_node = tagNodeRepository.findByName(random_type);
//                    if (random_tag_node == null) {
//                        random_tag_node = new TagNode(random_type);
//                    }
//                    bookNode.containTag(random_tag_node);
//                    tagNodeRepository.save(random_tag_node);
//                    break;
//                }
//                bookNodeRepository.save(bookNode);
//
//            }
//        };
    }
    public static void main(String[] args) {
        // springApplication: spring?????????    ??????: ????????????springboot??????
        // ??????1: ??????????????? ?????????   ??????2: main???????????????
        SpringApplication.run(Application.class, args);
    }
}
