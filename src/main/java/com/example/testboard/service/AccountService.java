package com.example.testboard.service;

import com.example.testboard.mapper.AccountMapper;
import com.example.testboard.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<UserDto> allUser() {
        return accountMapper.selectAll();
    }

    public Long getUserIdx(String userId){
        return accountMapper.findIdxByUserId(userId);
    }
}
