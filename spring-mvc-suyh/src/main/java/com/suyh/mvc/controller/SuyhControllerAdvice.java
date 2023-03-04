package com.suyh.mvc.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 它需要是一个spring 的bean 对象，并且添加注解@ControllerAdvice
 */
@ControllerAdvice
public class SuyhControllerAdvice {

    /**
     * 全局的initBinder
     *
     * 在url 中输入某个数据格式为`yyyy-MM-dd HH:mm:ss` 的日期数据时，可以将其绑定到Date 参数值上面。
     * 如：http://localhost:8080/spring_mvc_suyh_war_exploded/suyh//initBinder/date?date=2023-03-04%2021:14:00
     */
    @InitBinder("date")
    public void initBinderDate(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
}
