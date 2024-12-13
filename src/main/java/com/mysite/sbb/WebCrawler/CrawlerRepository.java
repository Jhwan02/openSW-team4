package com.mysite.sbb.WebCrawler;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrawlerRepository extends JpaRepository<WebCrawlerEntity, Long> {

    // ����(title) ���� ���η� Competition �˻�
    List<WebCrawlerEntity> findByTitleContaining(String keyword);

    List<WebCrawlerEntity> findAllByOrderByIdAsc();

}