package com.mysite.sbb.WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebCrawler {

    @Autowired
    private CrawlerRepository crawlerRepository; // 타입을 명시적으로 선언

    // @Scheduled(fixedRate = 3600000) // 1시간마다 실행
    public void crawlAndSaveCompetitions() {
        String url = "https://www.campuspick.com/contest?category=108";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements items = doc.select("list .item");

            for (Element item : items) {
                String imageUrl = item.select("figure").attr("data-image");
                String title = item.select(".top h2").text();
                String date = item.select(".info").text();

                // Save directly to database
                WebCrawlerEntity entity = new WebCrawlerEntity(imageUrl, title, date,0);

                // Save using CrawlerRepository
                crawlerRepository.save(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}