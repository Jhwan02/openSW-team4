package com.mysite.sbb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebCrawler {

    public static void main(String[] args) {
        String url = "https://linkareer.com/list/activity?filterBy_interestIDs=13&filterType=INTEREST&orderBy_direction=DESC&orderBy_field=CREATED_AT&page=1";

        try {
            // 웹 페이지를 가져와서 Document 객체로 변환
            Document doc = Jsoup.connect(url).get();

            // 특정 클래스 이름에 해당하는 정보를 가져오기
            Elements items = doc.select(".activity-list-card-item-wrapper");

            for (Element item : items) {
                // 이미지 링크, 제목, 날짜 추출
                String imageUrl = item.select(".image-link img").attr("src");
                String title = item.select(".activity-title").text();
                String date = item.select(".SecondInfoText__StyledWrapper-sc-16c35a9-0").text();

                // 추출한 정보 출력
                System.out.println("Image URL: " + imageUrl);
                System.out.println("Title: " + title);
                System.out.println("Date: " + date);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
