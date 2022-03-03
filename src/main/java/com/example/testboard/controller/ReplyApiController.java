package com.example.testboard.controller;

import com.example.testboard.service.AccountService;
import com.example.testboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController {
    private final ReplyService replyService;
    private final AccountService accountService;

    @GetMapping("/list")
    public List<Map<String, Object>> replyList(@RequestParam Long bIdx){
        return replyService.findBoardReply(bIdx);
    }

    @GetMapping("/list/{bIdx}")
    public List<Map<String, Object>> replyLists(@PathVariable Long bIdx){
        return replyService.findBoardReply(bIdx);
    }

    @PostMapping("/save")
    public void insert(@RequestBody Map<String,Object> content,
                       Authentication authentication){
        Long uIdx = accountService.getUserIdx(authentication.getName());
        content.put("uIdx", uIdx);
        System.out.println(content);
        replyService.insertReply(content);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam Long idx){
        replyService.deleteReply(idx);
    }
}
