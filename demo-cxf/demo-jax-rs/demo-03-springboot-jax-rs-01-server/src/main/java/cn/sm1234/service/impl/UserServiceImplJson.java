package cn.sm1234.service.impl;


import cn.sm1234.domain.User;
import cn.sm1234.service.UserServiceJson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplJson implements UserServiceJson {
    @Override
    public void saveUser(User user) {
        System.out.println("保存用户：" + user);
    }

    @Override
    public void updateUser(User user) {
        System.out.println("更新用户：" + user);
    }

    @Override
    public void deleteUser(Integer id) {
        System.out.println("删除用户：" + id);
    }

    @Override
    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "张三", "男"));
        users.add(new User(2, "李四", "男"));
        users.add(new User(3, "陈六", "女"));
        return users;
    }

    @Override
    public User findById(Integer id) {
        return new User(1, "露西", "男");
    }
}
