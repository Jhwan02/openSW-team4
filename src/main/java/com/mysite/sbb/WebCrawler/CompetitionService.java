package com.mysite.sbb.WebCrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

    private final CrawlerRepository crawlerRepository;

    @Autowired
    public CompetitionService(CrawlerRepository crawlerRepository) {
        this.crawlerRepository = crawlerRepository;
    }

    // 모든 Competition 데이터를 가져오는 메서드
    public List<WebCrawlerEntity> getCompetitions() {
        return crawlerRepository.findAll();
    }

    // 특정 키워드를 포함하는 Competition 데이터를 검색하는 메서드
    public List<WebCrawlerEntity> searchCompetitionsByTitle(String keyword) {
        return crawlerRepository.findByTitleContaining(keyword);
    }

    // 날짜를 기준으로 내림차순 정렬된 Competition 데이터를 가져오는 메서드
    public List<WebCrawlerEntity> getCompetitionsOrderByDate() {
        return crawlerRepository.findAllByOrderByDateDesc();
    }

    // 새로운 Competition 데이터를 저장하는 메서드
    public WebCrawlerEntity saveCompetition(WebCrawlerEntity competition) {
        return crawlerRepository.save(competition);
    }
}