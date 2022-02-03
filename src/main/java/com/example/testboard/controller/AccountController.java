package com.example.testboard.controller;

import com.example.testboard.model.dto.UserDto;
import com.example.testboard.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(){
        return "/account/login";
    }

    @GetMapping("/register")
    public String register(){
        return "/account/register";
    }

    @PostMapping("/register")
    public String register(UserDto userDto){
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userDto.getUserId());
        map.put("password", passwordEncoder.encode(userDto.getPassword()));
        map.put("idx", null);
        accountService.save(map);
        return "redirect:/";
    }
}
