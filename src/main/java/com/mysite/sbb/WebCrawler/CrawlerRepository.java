package com.mysite.sbb.WebCrawler;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrawlerRepository extends JpaRepository<WebCrawlerEntity, Long> {

    // 제목(title) 포함 여부로 Competition 검색
    List<WebCrawlerEntity> findByTitleContaining(String keyword);

    // 날짜(date) 기준 내림차순으로 모든 Competition 반환
    List<WebCrawlerEntity> findAllByOrderByDateDesc();
}
