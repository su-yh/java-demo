package com.mashibing.scan;

import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 对于xml 配置文件是的 context:component-scan 标签的解析
 * 首先通过定制的xml 解析处理类，将其解析处理。
 * 这里的定制的xml 解析处理类，它将指定扫描包下的类进行处理，包括这些类上面的注解信息，按相关的注解参数进行解析。
 * 最后会将{@link ConfigurationClassPostProcessor} 注册到beanFactory 中。
 * 然后在beanFactory 的后置处理调用的时候，对所有扫描到的bean 对象再次进行解析，包括不属于指定包中的配置轩。
 * 如果这些类上面有相关的配置注解，或者扫描注解(主要就是处理扫描注解，因为其他的都已经处理了)，那么它会将这些扫描包下的所有类再进行解析。
 */
public class TestScan {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("scanContext.xml");
    }
}
