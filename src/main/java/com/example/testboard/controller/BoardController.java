package com.example.testboard.controller;

import com.example.testboard.service.BoardService;
import com.example.testboard.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping("/list")
    public String listPage(){
        return "/board/boardList";
    }

    @GetMapping("/form")
    public String formPage(Authentication authentication){
        System.out.println(authentication.getAuthorities());
        return "/board/form";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Long idx, Model model){
        Map<String, Object> boardMap = boardService.getDetail(idx);
        List<Map<String, Object>> fileMaps = fileService.getDetail(idx);
        model.addAttribute("boardMap", boardMap);
        model.addAttribute("fileMaps", fileMaps);
        return "/board/boardDetail";
    }
}
