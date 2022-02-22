package com.example.testboard.controller;

import com.example.testboard.model.dto.BoardDto;
import com.example.testboard.service.AccountService;
import com.example.testboard.service.BoardService;
import com.example.testboard.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    private final ScheduleService scheduleService;
    private final AccountService accountService;

    @GetMapping("/index")
    public List<BoardDto> index(@RequestParam int catNum){
        return boardService.getFiveRow(catNum);
    }

    @GetMapping("/scheduleList")
    public List<Map<String, Object>> sIndex(Authentication authentication){
        Long userIdx = accountService.getUserIdx(authentication.getName());
        return scheduleService.getTodaySchedule(userIdx);
    }
}
