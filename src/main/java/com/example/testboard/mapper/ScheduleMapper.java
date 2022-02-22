package com.example.testboard.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScheduleMapper {
    void save(Map<String, Object> map1);

    List<Map<String, Object>> list(Map<String, Object> map);

    List<Map<String, Object>> getToday(Long userIdx);

    void delete(Long sIdx);
}
