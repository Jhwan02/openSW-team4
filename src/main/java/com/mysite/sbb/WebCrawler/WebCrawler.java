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
        String url = "https://www.wevity.com/?c=find&s=1&gub=1&cidx=21"; // 크롤링할 URL

        try {
            // HTML 문서 가져오기
            Document doc = Jsoup.connect(url).get();

            // 각 카드 항목 선택
            Elements items = doc.select("li.bg");

            for (Element item : items) {
                // 제목 추출 (a 태그 안의 텍스트)
                String title = item.select("div.tit a").text();

                // 날짜 추출 (class="day")
                String date = item.select("div.day").text();

                // 출력 (디버깅용)
                System.out.println("Title: " + title);
                System.out.println("Date: " + date);
                System.out.println("-----------------------------");

                // 데이터베이스 저장용 엔티티 생성
                WebCrawlerEntity entity = new WebCrawlerEntity(title, date);
                crawlerRepository.save(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
