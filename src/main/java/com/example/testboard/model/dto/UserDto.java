package com.example.testboard.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long idx;
    private String userId;
    private String password;

    private List<UserRoleDto> userRoles;

    public UserDto(UserDto request) {
        idx = request.getIdx();
        userId = request.getUserId();
        password = request.getPassword();
    }
}
