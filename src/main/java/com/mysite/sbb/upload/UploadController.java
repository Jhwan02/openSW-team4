package com.mysite.sbb.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${com.example.upload.path}") // application.properties에서 업로드 경로를 가져옴
    private String uploadPath;

    // 이미지 파일 업로드 처리
    @PostMapping("/uploadAjax")
    public UploadResultDTO uploadFile(@RequestParam("file") MultipartFile uploadFile) {

        // 이미지 파일만 업로드 가능
        if (!uploadFile.getContentType().startsWith("image")) {
            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
        }

        // 원본 파일 이름
        String originalName = uploadFile.getOriginalFilename();
        // UUID를 사용하여 중복 파일명 방지
        String fileName = UUID.randomUUID().toString() + "_" + originalName;

        // 파일 저장 경로
        Path savePath = Paths.get(uploadPath, fileName);

        // 업로드 경로가 존재하지 않으면 생성
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            // 파일 저장
            uploadFile.transferTo(savePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.");
        }

        // 업로드된 파일의 URL 반환
        String imageUrl = "/images/" + fileName; // 인코딩 없이 URL 생성

        // UploadResultDTO로 업로드 결과 반환
        return new UploadResultDTO(imageUrl);
    }
}
