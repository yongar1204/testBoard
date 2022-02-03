package com.example.testboard.service;

import com.example.testboard.mapper.AccountMapper;
import com.example.testboard.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;
    public void save(Map<String, Object> map) {
        accountMapper.insertUser(map);
        Long userId = Long.parseLong(String.valueOf(map.get("idx")));
        accountMapper.userRoleSave(userId);
    }
}
