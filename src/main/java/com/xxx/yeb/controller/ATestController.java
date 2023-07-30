package com.xxx.yeb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试使用
 */
@RestController
public class ATestController {

    @GetMapping("/test")
    public String test(){
        return "HelloSwagger";
    }

    @GetMapping("/employee/basic/hello")
    public String test1(){
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String test2(){
        return "/employee/advanced/hello";
    }
}
