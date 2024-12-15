package com.mysite.sbb.WebCrawler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import com.mysite.sbb.NewWebCrawler.HtmlCrawler;
import org.springframework.http.ResponseEntity;

@Controller
public class CompetitionController {

    private final CompetitionService competitionService;
    private final WebCrawler webCrawler;

    public CompetitionController(CompetitionService competitionService, WebCrawler webCrawler) {
        this.competitionService = competitionService;
        this.webCrawler = webCrawler;
    }

    @GetMapping("/competitions")
    public String getCompetitions(Model model) {
        // 크롤링 실행
        webCrawler.crawlAndSaveCompetitions();
        // DB에서 데이터 불러오기
        List<WebCrawlerEntity> competitions = competitionService.getCompetitionsByReverseSavedOrder();
        model.addAttribute("competitions", competitions);
        return "competitions"; // competitions.html로 전달
    }
}