package com.mysite.sbb.question;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {

    // 고정된 업로드 경로 설정
    private static final String uploadPath = "C:/upload";

    // 파일 업로드 및 URL 생성
    public String uploadFile(MultipartFile file) {
        // 원본 파일 이름
        String originalName = file.getOriginalFilename();

        // 파일 저장 경로 (C:/upload/파일명)
        Path savePath = Paths.get(uploadPath, originalName);

        try {
            // 부모 디렉토리 생성
            Files.createDirectories(savePath.getParent());
            // 파일 저장
            file.transferTo(savePath.toFile());


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed");
        }

        // 반환할 URL 생성
        return "/files/" + originalName;  // URL 반환 (파일 이름 그대로 사용)
    }
}
