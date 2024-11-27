package com.mysite.sbb.WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebCrawler {

    @Autowired
    private CrawlerRepository crawlerRepository; // 타입을 명시적으로 선언

    public void crawlAndSaveCompetitions() {
        String url = "https://linkareer.com/list/activity?filterBy_interestIDs=13&filterType=INTEREST&orderBy_direction=DESC&orderBy_field=CREATED_AT&page=1";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements items = doc.select(".activity-list-card-item-wrapper");

            for (Element item : items) {
                String imageUrl = item.select(".image-link img").attr("src");
                String title = item.select(".activity-title").text();
                String date = item.select(".SecondInfoText__StyledWrapper-sc-16c35a9-0").text();

                // Save directly to database
                WebCrawlerEntity entity = new WebCrawlerEntity(imageUrl, title, date);
                
                // Save using CrawlerRepository
                crawlerRepository.save(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
