package com.mysite.sbb.NewWebCrawler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.sbb.WebCrawler.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class CrawlerService {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerService.class);

    @Autowired
    private CrawlerRepository competitionRepository;

    public void saveCrawledData() {
        logger.debug("(CrawlerService) saveCrawledData() 호출됨.");
        JsonObject jsonData = HtmlCrawler.crawlHtml();

        if (jsonData != null) {
            try {
                JsonArray jsonArray = jsonData.getAsJsonObject("result").getAsJsonArray("activities");

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                    String title = jsonObject.has("title") ? jsonObject.get("title").getAsString() : "N/A";
                    String date = jsonObject.has("endDate") ? jsonObject.get("endDate").getAsString() : "N/A";
                    String imageUrl = jsonObject.has("image") ? jsonObject.get("image").getAsString() : "N/A";

                    // D-Day 계산
                    String dDay = calculateDDay(date);

                    // 중복 검사
                    if (!competitionRepository.existsByTitleAndDate(title, dDay)) {
                        WebCrawlerEntity competition = new WebCrawlerEntity();
                        competition.setTitle(title);
                        competition.setDate(dDay); // D-Day 값으로 저장
                        competition.setImageUrl(imageUrl);

                        competitionRepository.save(competition);
                        logger.debug("(CrawlerService) 데이터 저장 완료: {}", competition);
                    } else {
                        logger.debug("(CrawlerService) 중복 데이터 발견, 저장하지 않음: title={}, date={}", title, dDay);
                    }
                }
                logger.debug("(CrawlerService) 크롤링 데이터 {}개 저장 완료", jsonArray.size());
            } catch (Exception e) {
                logger.error("(CrawlerService) JSON 파싱 중 오류 발생: ", e);
            }
        } else {
            logger.error("(CrawlerService) HtmlCrawler.crawlHtml()가 null을 반환했습니다.");
        }
    }

    private String calculateDDay(String endDate) {
        try {
            // endDate가 "yyyy-MM-dd" 형식이라고 가정
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate eventDate = LocalDate.parse(endDate, formatter);
            LocalDate today = LocalDate.now();

            long daysBetween = ChronoUnit.DAYS.between(today, eventDate);

            if (daysBetween > 0) {
                return "D-" + daysBetween;
            } else if (daysBetween == 0) {
                return "D-Day";
            } else {
                return "D+" + Math.abs(daysBetween);
            }
        } catch (Exception e) {
            logger.error("(CrawlerService) D-Day 계산 중 오류 발생: ", e);
            return "Invalid Date";
        }
    }
}

