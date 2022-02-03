package com.example.testboard.controller;

import com.example.testboard.model.dto.BoardDto;
import com.example.testboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class indexApiController {
    private final BoardService boardService;

    @GetMapping("/index")
    public List<BoardDto> index(@RequestParam int catNum){
        return boardService.getFiveRow(catNum);
    }
}
