package com.example.mybatis.mapper;

import com.example.mybatis.domain.User;

public interface UserMapper {
    /**
     * 获取分页
     * @param phone 电话
     * @return
     */
    User selectUserById(String id);
}

