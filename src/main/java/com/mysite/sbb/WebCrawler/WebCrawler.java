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
    private CrawlerRepository crawlerRepository;

    public void crawlAndSaveCompetitions() {
        String url = "https://www.campuspick.com/contest?category=108";

        try {
            // HTML 문서 가져오기
            Document doc = Jsoup.connect(url).get();

            // 각 공모전 정보를 포함하는 요소 가져오기
            Elements items = doc.select(".list .item");

            for (Element item : items) {
                // 제목 추출
                String title = item.select("h2").text();

                // 날짜 추출
                String date = item.select("span.dday").text();

                // 이미지 URL 추출
                String imageUrl = item.select("figure").attr("data-image");

                // 데이터베이스 저장용 엔티티 생성
                WebCrawlerEntity entity = new WebCrawlerEntity(title, date, imageUrl);
                crawlerRepository.save(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
