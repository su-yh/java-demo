package com.example.mybatis;

import com.example.mybatis.domain.User;
import com.example.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {
    public static void main(String[] args) throws IOException {
        prepare();
        selectUserById();
        System.out.println("结束 了");
    }
    /**
     * @Test 方法启动之前先加载 sqlSessionFactory对象
     */
    private static SqlSessionFactory sqlSessionFactory;

    public static void prepare() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static void selectUserById() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectUserById("abc-id");
        // User(id=1, username=null, password=123456, address= "北京", phone= "1008611")
        System.out.println(user);
    }
}
