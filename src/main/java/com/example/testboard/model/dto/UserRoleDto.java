package com.example.testboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    private Long idx;
    private Long userId;
    private Long roleId;

    private RoleDto role;
}
