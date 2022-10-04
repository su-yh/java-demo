package com.mashibing.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("suyhFactoryBean.xml");

        // 以"&" 开头的都被认为是取factoryBean 的实例对象。
        SuyhFactoryBean factoryBean = (SuyhFactoryBean) ac.getBean("&suyhUser");
        System.out.println(factoryBean);
        SuyhUser suyhUser = (SuyhUser) ac.getBean("suyhUser");
        System.out.println(suyhUser);
    }
}
