package com.mysite.sbb.WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component // Spring Component로 선언 -> 스프링이 관리하는 객체가 됨
public class WebCrawler {

    @Autowired // CrawlerRepository를 주입받음 (Spring Data JPA로 데이터베이스와 연결)
    private CrawlerRepository crawlerRepository;

    public void crawlAndSaveCompetitions() { // 크롤링 작업을 수행하고 데이터를 데이터베이스에 저장하는 메서드
        String url = "https://www.contestkorea.com/sub/list.php?int_gbn=1&Txt_bcode=030510001";

        try {
            // HTML 문서 가져오기
            Document doc = Jsoup.connect(url).get(); // HTML 문서 파싱

            // 제목 가져오기 (공모전 제목)
            Elements titles = doc.select(".list_style_2 li .title span.txt");
            // 날짜 가져오기 (남은 기간)
            Elements dates = doc.select(".list_style_2 li .date");
            // 이미지 가져오기 (이미지가 포함된 태그)
           // Elements images = doc.select(".list_style_2 li img");

            // 데이터 크기 동기화
            int count = Math.min(titles.size(), (dates.size()));

            for (int i = 0; i < count; i++) {
                String title = titles.get(i).text(); // 제목
                String date = dates.get(i).text(); // 남은 기간
               // String imageUrl = images.get(i).attr("src"); // 이미지 URL

                // 데이터베이스 저장용 엔티티 생성
                WebCrawlerEntity entity = new WebCrawlerEntity(title, date);
                crawlerRepository.save(entity); // 데이터베이스에 저장
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
