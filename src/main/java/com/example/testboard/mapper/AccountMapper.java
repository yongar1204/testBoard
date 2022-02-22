package com.example.testboard.mapper;

import com.example.testboard.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper {
    UserDto findByUserId(String userId);

    void insertUser(Map<String, Object> map);

    void userRoleSave(Long userId);

    Long findIdxByUserId(String userId);

    List<UserDto> selectAll();
}
