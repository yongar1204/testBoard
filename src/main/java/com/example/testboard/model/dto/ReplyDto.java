package com.example.testboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDto {
    private Long idx;
    private String content;

    private BoardDto board;
    private UserDto user;
}
