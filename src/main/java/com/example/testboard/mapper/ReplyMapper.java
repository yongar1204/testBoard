package com.example.testboard.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyMapper {
    List<Map<String, Object>> findReply(Long bIdx);

    void insertReply(Map<String, Object> content);

    void deleteReply(Long idx);
}
