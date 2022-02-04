package com.example.testboard.controller;

import com.example.testboard.mapper.AccountMapper;
import com.example.testboard.model.Criteria;
import com.example.testboard.model.dto.BoardDto;
import com.example.testboard.model.dto.CategoryDto;
import com.example.testboard.service.BoardService;
import com.example.testboard.service.FileService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.script.ScriptEngine;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;
    private final FileService fileService;
    private final AccountMapper accountMapper;

    @PostMapping("/create")
    public Map<String, Object> submit2(@RequestParam(required = false, value = "img") MultipartFile[] files,
                                       Authentication authentication,
                                       @RequestPart(required = false, value = "Json") String m2) throws IOException {
        HashMap<String, Object> m3 = new ObjectMapper().readValue(m2, HashMap.class);
        Long idx = accountMapper.findIdxByUserId(authentication.getName());
        m3.put("idx", null);
        m3.put("userIdx", idx);
        System.out.println(m3);
        boardService.create(m3, files);
        return m3;
    }

    @GetMapping("/category")
    public List<CategoryDto> getCategory(){
        return boardService.getCategory();
    }

    @GetMapping("/list")
    public List<BoardDto> getList(@RequestParam String ctg,
                                  @RequestParam(required = false, defaultValue = "1") int pn){
        List<BoardDto> findAll = boardService.getList(ctg);
        Criteria criteria = new Criteria(pn);
        criteria.setTotalElements(findAll.size());
        criteria.setTotalPages((int) Math.ceil(criteria.getTotalElements()/(double)criteria.getRecords()));
        criteria.setLastPage(Math.min(criteria.getTotalPages(), (criteria.getStartPage()+criteria.getDisplayPageNum()-1)));
        Map<String, Object> listMap = new HashMap<>();
        List<BoardDto> pagingList = boardService.getPagingList(ctg, criteria);
        listMap.put("paging",criteria);
        listMap.put("pagingList", pagingList);
        System.out.println(listMap.get("paging"));
        System.out.println(listMap.get("pagingList"));
//        map boardDto와 paging 함께 담기
        return findAll;

    }
    @GetMapping("/test")
    public Map<String, Object> getLists(@RequestParam String ctg,
                                  @RequestParam(required = false, defaultValue = "1") int pn) {
        List<BoardDto> findAll = boardService.getList(ctg);
        Criteria criteria = new Criteria(pn);
        criteria.setTotalElements(findAll.size());
        criteria.setTotalPages((int) Math.ceil(criteria.getTotalElements() / (double) criteria.getRecords()));
        criteria.setLastPage(Math.min(criteria.getTotalPages(), (criteria.getStartPage() + criteria.getDisplayPageNum() - 1)));
        Map<String, Object> listMap = new HashMap<>();
        List<BoardDto> pagingList = boardService.getPagingList(ctg, criteria);
        listMap.put("paging", criteria);
        listMap.put("pagingList", pagingList);
//        map boardDto와 paging 함께 담기
        return listMap;
    }
}
