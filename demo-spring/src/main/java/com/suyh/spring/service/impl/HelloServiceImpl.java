package com.suyh.spring.service.impl;


import com.suyh.spring.service.HelloService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 苏雲弘
 * @since 2021-01-07
 */
public class HelloServiceImpl implements HelloService {

    public String say() {
        return "hello";
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }
}
