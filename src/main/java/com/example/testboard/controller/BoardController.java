package com.example.testboard.controller;

import com.example.testboard.model.dto.CategoryDto;
import com.example.testboard.service.BoardService;
import com.example.testboard.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping("/list")
    public String listPage(){
        return "/board/boardList";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "/admin";
    }

    @GetMapping("/form")
    public String formPage(Authentication authentication,
                           Model model){
//        List<CategoryDto> catName = boardService.getCategory();
        List<Map<String, Object>> catName = boardService.getCategoryList();
        model.addAttribute("catName", catName);
        model.addAttribute("authentication", authentication);
        System.out.println(authentication.getAuthorities() + authentication.getName() + authentication.getPrincipal());
        return "/board/form";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Long idx, Model model, Authentication authentication){
        Map<String, Object> boardMap = boardService.getDetail(idx);
        List<Map<String, Object>> fileMaps = fileService.getDetail(idx);
        model.addAttribute("authentication", authentication);
        model.addAttribute("boardMap", boardMap);
        model.addAttribute("fileMaps", fileMaps);
        return "/board/detail";
    }

    @GetMapping("/detail/{bIdx}")
    public String detailBoard(@PathVariable Long bIdx,
                              Model model,
                              Authentication authentication){
        model.addAttribute("getBoardDetails", boardService.getDetailBoard(bIdx));
        model.addAttribute("authentication", authentication);
        return "/board/detail2";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long idx,
                       Model model,
                       Authentication authentication){
        List<CategoryDto> catName = boardService.getCategory();
        Map<String, Object> boardMap = boardService.getDetail(idx);
        List<Map<String, Object>> fileList = fileService.getDetail(idx);
        model.addAttribute("catName", catName);
        model.addAttribute("authentication", authentication);
        model.addAttribute("boardMap", boardMap);
        model.addAttribute("fileList", fileList);
        return "/board/editForm";
    }

    @GetMapping("/edit2")
    public String edit2(@RequestParam Long idx){
        return "/board/editForm2";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long idx){
        boardService.deleteBoard(idx);
        return "redirect:/board/list";
    }

    @GetMapping("/downLoad/{idx}")
    public void fileDownload(@PathVariable Long idx, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> fileInfo = fileService.getFile(idx);
        String fileName = (String) fileInfo.get("file_name");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode((String) fileInfo.get("orig_file_name"), "UTF-8") + "\";");
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(fileInfo.get("file_path") +"/"+ fileName);

            int count = 0;
            byte[] bytes = new byte[1024];

            while ((count = fis.read(bytes)) != -1 ) {
                os.write(bytes, 0, count);
            }

            fis.close();
            os.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
