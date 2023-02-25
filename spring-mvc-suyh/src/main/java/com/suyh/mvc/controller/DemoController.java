package com.suyh.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/suyh")
public class DemoController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping("/file/upload")
    @ResponseBody
    public String fileUpload(@RequestPart(value = "file") MultipartFile upload) {
        if (upload == null) {
            return "failed";
        }
        return upload.getOriginalFilename();
    }
}
