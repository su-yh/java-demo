package com.mashibing.selfConverter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConverterMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("converterContext.xml");
    }
}
