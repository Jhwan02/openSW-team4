package com.mysite.sbb.NewWebCrawler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.sbb.WebCrawler.WebCrawlerEntity;
import com.mysite.sbb.login.AuthController;

@RestController
@RequestMapping("/api/crawler") // 공통 URL 경로

public class HtmlCrawlerController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private CrawlerService crawlerService;
    // GET 요청으로 데이터 반환
    @GetMapping("/datasave")
    
    public ResponseEntity<String> getCrawledData() {
        //크롤링 요청 디버그
        logger.debug("(HtmlCrawlerController) getCrawledData() 호출됨.");
        crawlerService.saveCrawledData();

        return ResponseEntity.ok("크롤링 데이터 저장 완료");
    }

   @GetMapping("/top3")
    public ResponseEntity<List<WebCrawlerEntity>> getTop3Data() {
    logger.debug("(HtmlCrawlerController) getTop3Data() 호출됨.");

    // JSON 형식으로 반환
    return crawlerService.getTop3Data();
}

}

