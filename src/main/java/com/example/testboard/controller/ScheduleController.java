package com.example.testboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    @GetMapping("form")
    public String Schedules(){
        return "/schedules/scheduleForm";
    }
}
