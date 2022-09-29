package com.mashibing.selfEditor;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.support.ResourceEditorRegistrar;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义属性编辑器，可以将配置文件中的字符串解析成一个vo 对象(Address)，并将其注入到bean 的属性中。
 * <ol>
 * <li>{@link AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)} 预处理的时候添加了spring 实现的{@link ResourceEditorRegistrar}</li>
 * <li>{@link CustomEditorConfigurer} 中配置用户实现的{@link ResourceEditorRegistrar} 以{@link BeanFactoryPostProcessor} 的身份</li>
 * </ol>
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
