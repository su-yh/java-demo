package com.mashibing;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestScan {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("scanContext.xml");
    }
}
