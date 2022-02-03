package com.example.testboard.service;

import com.example.testboard.mapper.FileMapper;
import com.example.testboard.model.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileMapper fileMapper;
    public void attFileSave(MultipartFile f, Long boardIdx) throws IOException {
        if (!f.isEmpty()) {
            String projectPath = System.getProperty("user.dir") + "/files";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + f.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            f.transferTo(saveFile);

            Map<String, Object> map = new HashMap<>();
            map.put("boardIdx", boardIdx);
            map.put("fileName", fileName);
            map.put("origFileName", f.getOriginalFilename());
            map.put("filePath", projectPath);
            fileMapper.save(map);
        } else new FileDto();
    }

    public List<Map<String, Object>> getDetail(Long board_idx) {
        return fileMapper.getFiles(board_idx);
    }
}
