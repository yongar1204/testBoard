package com.example.testboard.service;

import com.example.testboard.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyMapper replyMapper;

    public List<Map<String, Object>> findBoardReply(Long bIdx) {
        return replyMapper.findReply(bIdx);
    }

    public void insertReply(Map<String, Object> content){
        replyMapper.insertReply(content);
    }

    public void deleteReply(Long idx) {
        replyMapper.deleteReply(idx);
    }
}
