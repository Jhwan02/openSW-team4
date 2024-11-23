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
            // �� �������� �����ͼ� Document ��ü�� ��ȯ
            Document doc = Jsoup.connect(url).get();

            // Ư�� Ŭ���� �̸��� �ش��ϴ� ������ ��������
            Elements items = doc.select(".activity-list-card-item-wrapper");

            for (Element item : items) {
                // �̹��� ��ũ, ����, ��¥ ����
                String imageUrl = item.select(".image-link img").attr("src");
                String title = item.select(".activity-title").text();
                String date = item.select(".SecondInfoText__StyledWrapper-sc-16c35a9-0").text();

                // ������ ���� ���
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
