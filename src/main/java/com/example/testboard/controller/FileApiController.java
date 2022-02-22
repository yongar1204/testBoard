package com.example.testboard.controller;

import com.example.testboard.service.BoardService;
import com.example.testboard.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileApiController {
    private final FileService fileService;

    @GetMapping("/downLoad")
    public void fileDownload(@RequestParam Long idx, HttpServletResponse response) throws UnsupportedEncodingException {
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
