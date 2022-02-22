package com.example.testboard.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {
    void save(Map<String, Object> map);

    List<Map<String, Object>> getFiles(Long board_idx);

    Map<String, Object> getFile(Long idx);
}
