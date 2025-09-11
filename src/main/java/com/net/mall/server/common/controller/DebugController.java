package com.net.mall.server.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @GetMapping("/check-static-resource")
    public ResponseEntity<String> checkStaticResource() {
        String filePath = "E:/net-mall-upload/myImg/f9baa5ff-a831-409a-be7b-c93d1df9d791.png";
        File file = new File(filePath);

        if (file.exists()) {
            return ResponseEntity.ok("文件存在，路径: " + filePath);
        } else {
            return ResponseEntity.status(404).body("文件不存在，路径: " + filePath);
        }
    }
}