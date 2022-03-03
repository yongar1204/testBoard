package com.example.testboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/1")
    public String t1(){
        return "/test";
    }
    @GetMapping("/2")
    public String adminT(){
        return "/admin";
    }
    @GetMapping("/index")
    public String t2(){
        return "/index";
    }
    @GetMapping("/sc")
    public String sc(){
        return "/board/boardForm";
    }
}
