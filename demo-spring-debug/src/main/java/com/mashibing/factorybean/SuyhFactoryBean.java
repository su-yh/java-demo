package com.mashibing.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class SuyhFactoryBean implements FactoryBean<SuyhUser> {

    @Override
    public SuyhUser getObject() throws Exception {
        //任何创建对象的操作
        return new SuyhUser("zhangsan");
    }

    @Override
    public Class<?> getObjectType() {
        return SuyhUser.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
