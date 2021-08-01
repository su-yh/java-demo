package cn.sm1234.main;

import cn.sm1234.domain.User;
import cn.sm1234.service.UserService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class DemoApplicationJaxWsClient01 {
    public static void main(String[] args) {
        // 1. 创建工厂对象
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        // 2. 设置参数
        // 2.1 设置访问路径
        factory.setAddress("http://localhost:8159/userService");
        // 2.2 设置接口(似乎接口的包名需要一致才行哟。)
        factory.setServiceClass(UserService.class);
        // 3. 创建接口的代理类对象
        UserService userService = (UserService) factory.create();

        // 4. 调用
        userService.saveUser(new User(1, "小张", "男"));
    }
}