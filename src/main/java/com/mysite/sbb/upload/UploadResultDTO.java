package com.mysite.sbb.upload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadResultDTO {
    private String imageURL; // 업로드된 이미지의 URL

    public UploadResultDTO(String imageURL) {
        this.imageURL = imageURL;
    }
}
