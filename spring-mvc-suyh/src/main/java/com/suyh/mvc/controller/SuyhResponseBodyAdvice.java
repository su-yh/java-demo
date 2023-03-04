package com.suyh.mvc.controller;

import com.suyh.mvc.controller.vo.Student;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class SuyhResponseBodyAdvice implements ResponseBodyAdvice<Student> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 在什么情况下需要处理返回值。
        return returnType.getParameterType().isAssignableFrom(Student.class);
    }

    @Override
    public Student beforeBodyWrite(Student body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("before Body write. body: " + body);
        return body;
    }
}
