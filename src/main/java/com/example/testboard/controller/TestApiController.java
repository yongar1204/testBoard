package com.example.testboard.controller;

import com.example.testboard.model.dto.TestDto;
import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/test/api")
public class TestApiController {

    @PostMapping("/map")
    public Map<String, Object> map(@RequestBody Map<String, Object> map) throws IOException {
        ArrayList<Map<String, Object>> fileList = (ArrayList<Map<String, Object>>) map.get("fileList");
        for (Map<String,Object> file : fileList){
            System.out.println(file.get("fileName"));
        }

        String fileName = (String) fileList.get(0).get("fileName");
        File file = new File(System.getProperty("user.dir") + "/files/" + fileName);
        String fileData = (String) fileList.get(0).get("fileData");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(fileData.split(",")[1]);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(decodedBytes);
        fileOutputStream.close();

        return map;
    }
    @PostMapping("/map1")
    public void map(@RequestBody TestDto dto){
        System.out.println(dto);
    }


}

