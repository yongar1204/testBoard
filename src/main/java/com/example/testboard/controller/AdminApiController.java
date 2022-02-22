package com.example.testboard.controller;

import com.example.testboard.model.dto.UserDto;
import com.example.testboard.service.AccountService;
import com.example.testboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApiController {
    private final BoardService boardService;
    private final AccountService accountService;

    @GetMapping("category")
    public List<String> categoryName(){
        return boardService.getCategoryName();
    }

    @PostMapping("cAdd")
    public void categoryAdd(@RequestParam String catName){
        System.out.println(catName);
        boardService.addCategory(catName);
    }

    @GetMapping("user")
    public List<UserDto> allUser(){
        return accountService.allUser();
    }

    @DeleteMapping("/deleteC")
    public void delete(@RequestParam String catName){
        boardService.deleteCategory(catName);
    }
}
