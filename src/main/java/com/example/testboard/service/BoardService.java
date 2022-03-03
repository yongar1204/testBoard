package com.example.testboard.service;

import com.example.testboard.mapper.BoardMapper;
import com.example.testboard.model.Criteria;
import com.example.testboard.model.dto.BoardDto;
import com.example.testboard.model.dto.CategoryDto;
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

    public void updateBoard(HashMap<String, Object> m3, MultipartFile[] files) throws IOException {
        boardMapper.updateBoard(m3);
        Long boardIdx = (Long) m3.get("idx");
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

    public List<BoardDto> getPagingList(String ctg, Criteria criteria) {
        Map<String, Object> map = new HashMap<>();
        map.put("ctg", ctg);
        map.put("records", criteria.getRecords());
        map.put("startPageNum", (criteria.getPageNum()-1)*criteria.getRecords());
        return boardMapper.getPagingList(map);
    }

    public void hitUp(Long boardIdx){
        boardMapper.hitUp(boardIdx);
    }

    public Map<String, Object> getBoardCategory(Long bIdx) {
        return boardMapper.getBoardCategory(bIdx);
    }

//    adminPage
    public List<String> getCategoryName() {
        return boardMapper.getCategoryName();
    }

    public void addCategory(String catName) {
        boardMapper.addCat(catName);
    }

    public void deleteBoard(Long idx) {
        boardMapper.delete(idx);
    }

    public List<Map<String, Object>> getCategoryList() {
        return boardMapper.getCategoryList();
    }

    public void deleteCategory(String catName) {
        boardMapper.deleteCategory(catName);
    }
// PathVariable
    public Map<String, Object> getDetailBoard(Long bIdx) {
        Map<String,Object> boardMap = new HashMap<>();
        boardMap.put("board", boardMapper.getDetail(bIdx));
        boardMap.put("files", fileService.getDetail(bIdx));
        return boardMap;
    }
}
