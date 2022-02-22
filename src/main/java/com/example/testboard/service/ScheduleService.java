package com.example.testboard.service;

import com.example.testboard.mapper.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleMapper scheduleMapper;

    public void save(Map<String, Object> map1) {
        scheduleMapper.save(map1);
    }

    public List<Map<String, Object>> list(Map<String, Object> map) {
        return scheduleMapper.list(map);
    }

    public List<Map<String, Object>> getTodaySchedule(Long userIdx) {
        return scheduleMapper.getToday(userIdx);
    }

    public void delete(Long sIdx) {
        scheduleMapper.delete(sIdx);
    }
}
