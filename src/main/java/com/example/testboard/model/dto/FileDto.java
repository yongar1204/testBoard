package com.example.testboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private Long idx;
    private String fileName;
    private String origFileName;
    private String filePath;
    private BoardDto board;
}
