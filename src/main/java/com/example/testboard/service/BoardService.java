package com.example.testboard.service;

import com.example.testboard.mapper.BoardMapper;
import com.example.testboard.model.dto.BoardDto;
import com.example.testboard.model.dto.CategoryDto;
import com.example.testboard.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileService fileService;

    public void create(HashMap<String, Object> map, MultipartFile[] files) throws IOException {
        boardMapper.save(map);
        Long boardIdx = Long.parseLong(String.valueOf(map.get("idx")));
        if (files != null){
            for (MultipartFile f : files) {
                fileService.attFileSave(f, boardIdx);
            }
        }
    }

    public List<CategoryDto> getCategory(){
        return boardMapper.getCategory();
    }

    public List<BoardDto> getList(String ctg) {
        List<BoardDto> list = boardMapper.getList(ctg);
//        for (BoardDto b : list){
//            Long bu = b.getUser().getIdx();
//            System.out.println(bu);
//        }
        return list;
    }

    public List<BoardDto> getFiveRow(int catNum){
        return boardMapper.fiveRow(catNum);
    }

    public Map<String, Object> getDetail(Long idx) {
        return boardMapper.getDetail(idx);
    }
}