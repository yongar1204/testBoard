package com.example.testboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/1")
    public String t1(){
        return "/board/boardList";
    }

    @GetMapping("/index")
    public String t2(){
        return "/index";
    }
}
