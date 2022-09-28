package com.mashibing.selfEditor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义属性编辑器，可以将配置文件中的字符串解析成一个vo 对象(Address)，并将其注入到bean 的属性中。
 */
public class CustomerEditorMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("selfEditor.xml");
        Customer customer01 = ac.getBean("customer01", Customer.class);
        System.out.println("customer01: " + customer01);

        Customer customer02 = ac.getBean("customer02", Customer.class);
        System.out.println("customer02: " + customer02);

    }
}
