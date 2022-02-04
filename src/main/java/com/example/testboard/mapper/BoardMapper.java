package com.example.testboard.mapper;

import com.example.testboard.model.dto.BoardDto;
import com.example.testboard.model.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    void save(HashMap<String, Object> map);

    List<CategoryDto> getCategory();

    List<BoardDto> getList(String ctg);

    List<BoardDto> fiveRow(int catNum);

    Map<String, Object> getDetail(Long idx);

    List<BoardDto> getPagingList(Map<String, Object> map);
}
