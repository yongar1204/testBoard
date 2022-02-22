package com.example.testboard.controller;

import com.example.testboard.service.AccountService;
import com.example.testboard.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleApiController {
    private final ScheduleService scheduleService;
    private final AccountService accountService;

    @GetMapping("/list")
    public List<Map<String, Object>> scheduleList(Authentication authentication,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startAt,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endAt){
        if (endAt == null){
            endAt = LocalDate.of(2999, 12, 31);
        }
        System.out.println(startAt);
        System.out.println(endAt);
        Long userIdx = accountService.getUserIdx(authentication.getName());
        Map<String, Object> sMap = new HashMap<>();
        sMap.put("userIdx", userIdx);
        sMap.put("startAt", startAt);
        sMap.put("endAt", endAt);
        List<Map<String, Object>> lm = scheduleService.list(sMap);
        System.out.println(lm);
        return lm;
    }

    @PostMapping("/summit")
    public void scheduleSummit(@RequestBody Map<String, Object> map1,
                               Authentication authentication){
        Long userIdx = accountService.getUserIdx(authentication.getName());
        map1.put("userIdx", userIdx);
        scheduleService.save(map1);
        System.out.println(map1);
    }

    @DeleteMapping("/delete")
    public void scheduleDelete(@RequestParam Long sIdx){
        scheduleService.delete(sIdx);
    }
}
