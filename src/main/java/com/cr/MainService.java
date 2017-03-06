package com.cr;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainService {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring-db.xml", "classpath:spring-aop.xml", "classpath:dubbo-server.xml" });
        context.start();
        String[] str = context.getBeanDefinitionNames();
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
