package com.mashibing.selfEditor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义属性编辑器，可以将配置文件中的字符串解析成一个vo 对象(Address)，并将其注入到bean 的属性中。
 */
public class CustomerEditorMain {
    public static void main(String[] args) {
        test01();
        test02();
    }

    private static void test01() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("selfEditor1_01.xml");
        CustomerBean customer = ac.getBean(CustomerBean.class);
        System.out.println("customer: " + customer);
    }

    private static void test02() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("selfEditor1_02.xml");
        CustomerBean customer = ac.getBean(CustomerBean.class);
        System.out.println("customer: " + customer);
    }
}
