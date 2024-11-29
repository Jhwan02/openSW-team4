package com.mysite.sbb.WebCrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CompetitionController {

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/competitions")
    public String getCompetitions(Model model) {
        // WebCrawlerEntity로 타입 변경
        List<WebCrawlerEntity> competitions = competitionService.getCompetitions();
        model.addAttribute("competitions", competitions);
        return "competitions"; // HTML 템플릿 파일 이름
    }
}
