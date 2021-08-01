package cn.sm1234.main;

import cn.sm1234.domain.User;
import org.apache.cxf.jaxrs.client.WebClient;

public class DemoApplicationJaxRsClient01 {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        WebClient.create("http://localhost:8159/userService")
                .post(new User(1, "小张", "男1"));
    }
}
