package com.mysite.sbb.NewWebCrawler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.JsonParser;

public class HtmlCrawler {

    public static JsonObject crawlHtml() {
        try {
            // HttpClient 객체 생성
            HttpClient client = HttpClient.newHttpClient();

            // HttpRequest 설정
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.campuspick.com/find/activity/list"))
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString("target=1&limit=20&offset=0")) // 요청 본문 추가
                    .build();

            // HTTP 요청 보내기
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // HTTP 응답 상태 확인
            if (response.statusCode() == 200) {
                System.out.println("응답 데이터 받기 성공!");

                // JsonReader로 파싱 (Lenient 모드 설정)
                JsonReader reader = new JsonReader(new StringReader(response.body()));
                reader.setLenient(true); // 비표준 JSON 허용

                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                return jsonObject;
            } else {
                System.out.println("HTTP Error Code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // 오류 시 null 반환
    }
}
