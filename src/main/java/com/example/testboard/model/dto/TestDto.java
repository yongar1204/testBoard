package com.example.testboard.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {
    private String title;
    private String content;
    private int category;
}
