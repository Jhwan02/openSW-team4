package com.mysite.sbb.WebCrawler;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrawlerRepository extends JpaRepository<WebCrawlerEntity, Long> {

    // 제목(title) 포함 여부로 Competition 검색
    List<WebCrawlerEntity> findByTitleContaining(String keyword);

    List<WebCrawlerEntity> findAllByOrderByIdAsc();
}