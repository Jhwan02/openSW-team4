package com.mysite.sbb.WebCrawler;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// WebCrawlerEntity 클래스는 웹 크롤링한 데이터를 저장하는 엔티티입니다.
@Entity
@Table(name = "WEB_CRAWLER_ENTITY")
public class WebCrawlerEntity {

    // id 필드는 기본 키로 사용되며, 자동 생성됩니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // imageUrl 필드는 이미지 URL을 저장합니다.
    // private String imageUrl;

    // title 필드는 제목을 저장합니다.
    private String title;

    // date 필드는 날짜를 저장합니다.
    private String date;

    // 기본 생성자
    public WebCrawlerEntity() {
    }

    // 매개변수가 있는 생성자
    public WebCrawlerEntity(String title, String date) {
        this.title = title;
        this.date = date;
    }

    // Getter 및 Setter 메서드
    // id의 Getter
    public Long getId() {
        return id;
    }

    // // imageUrl의 Getter
    // public String getImageUrl() {
    // return imageUrl;
    // }

    // // imageUrl의 Setter
    // public void setImageUrl(String imageUrl) {
    // this.imageUrl = imageUrl;
    // }

    // title의 Getter
    public String getTitle() {
        return title;
    }

    // title의 Setter
    public void setTitle(String title) {
        this.title = title;
    }

    // date의 Getter
    public String getDate() {
        return date;
    }

    // date의 Setter
    public void setDate(String date) {
        this.date = date;
    }
}