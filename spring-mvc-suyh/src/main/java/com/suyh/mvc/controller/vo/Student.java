package com.suyh.mvc.controller.vo;

import lombok.Data;

@Data
public class Student {
    private User user;

    private Integer number;
    private String gender;
}
