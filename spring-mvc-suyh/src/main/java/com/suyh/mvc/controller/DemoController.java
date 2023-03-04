package com.suyh.mvc.controller;

import com.suyh.mvc.controller.vo.Student;
import com.suyh.mvc.controller.vo.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Controller
@RequestMapping("/suyh")
public class DemoController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/file/upload", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String fileUpload(@RequestPart(value = "file") MultipartFile upload, @RequestParam("desc") String desc) {
        if (upload == null) {
            return "failed";
        }
        return upload.getOriginalFilename();
    }

    /**
     * InitBinder 注解参数对应的是@ModelAttribute 对应的参数
     * 或者方法参数名也可以
     */
    @InitBinder("user")
    public void bindUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("u.");
    }

    @InitBinder("stu1")
    public void init1(WebDataBinder binder) {
        // 这里的`s1.` 前缀是在URL 参数上面的前缀参数，或者表单参数
        binder.setFieldDefaultPrefix("s1.");
    }

    @InitBinder("stu2")
    public void init2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("s2.");
    }


    @RequestMapping(value = "/initBinder/date", method = RequestMethod.GET)
    @ResponseBody
    public String testInitBinderDate(Date date) {
        return date.toString();
    }

    /**
     * http://localhost:8080/spring_mvc_suyh_war_exploded/suyh/testBean
     * ?s1.number=1111&s1.gender=nan&s2.number=222&s2.gender=nv&s1.user.name=suyh&s1.user.age=33&u.name=name&u.age=11
     */
    @RequestMapping(value = "/testBean", method = RequestMethod.GET)
    @ResponseBody
    public String testBean(User user, @ModelAttribute("stu1") Student student, @ModelAttribute("stu2") Student student2) {
        System.out.println(user);
        System.out.println(student);
        System.out.println(student2);
        return "OK";
    }

    // 这个有问题，主要原因就是jackson 没有添加的原因吧，无法将实例对象转换成String
    @RequestMapping(value = "/custom/responseBodyAdvice", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Student customResponseBodyAdvice() {
        User user = new User();
        user.setName("suyh");
        user.setAge(11);

        Student stu = new Student();
        stu.setGender("nan");
        stu.setNumber(111);
        stu.setUser(user);
        return stu;
    }
}
