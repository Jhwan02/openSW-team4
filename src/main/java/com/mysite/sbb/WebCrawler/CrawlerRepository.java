package com.mysite.sbb.WebCrawler;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrawlerRepository extends JpaRepository<WebCrawlerEntity, Long> {

    List<WebCrawlerEntity> findByTitleContaining(String keyword);

    List<WebCrawlerEntity> findAllByOrderByIdAsc();

    boolean existsByTitle(String title);

    List<WebCrawlerEntity> findTop3ByOrderByViewDesc();

}