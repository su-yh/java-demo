package com.mashibing.selfEditor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerEditorMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("selfEditor.xml");
        Customer customer01 = ac.getBean("customer01", Customer.class);
        System.out.println("customer01: " + customer01);

        Customer customer02 = ac.getBean("customer02", Customer.class);
        System.out.println("customer02: " + customer02);

    }
}
