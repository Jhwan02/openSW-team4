package com.mysite.sbb.techInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class techBlogCrawler {
    @Autowired
    private blogPostRepository blogPostRepository;

    @Scheduled(fixedRate = 3600000) // 1시간마다 실행
    public void performCrawling() {
        String url = "https://techblogposts.com/ko"; // 크롤링할 페이지 URL

        try {
            // HTML 문서 파싱
            Document doc = Jsoup.connect(url).get();

            // 블로그 포스트 목록 선택
            Elements posts = doc.select("._1kme5q1");

            for (Element post : posts) {
                // 제목 및 링크 추출
                Element titleElement = post.selectFirst("a");
                if (titleElement != null) {
                    String title = titleElement.attr("title");
                    String link = titleElement.attr("href");

                    // 날짜 추출
                    Element dateElement = post.selectFirst("._1kme5q5 time");
                    String fullDateTime = dateElement.attr("datetime");
                    String date = fullDateTime.substring(0, 10);

                    // 이미지 URL 추출
                    Element imageElement = post.selectFirst("img"); // 이미지 태그
                    String imageUrl = imageElement != null ? imageElement.attr("src") : null;

                    // 데이터 저장
                    if (blogPostRepository.findByTitle(title).isEmpty()) {
                        blogPost blogPost = new blogPost();
                        blogPost.setTitle(title);
                        blogPost.setLink(link);
                        blogPost.setDate(date);
                        blogPost.setImageUrl(imageUrl); // 이미지 URL 저장

                        // DB에 저장
                        blogPostRepository.save(blogPost);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
