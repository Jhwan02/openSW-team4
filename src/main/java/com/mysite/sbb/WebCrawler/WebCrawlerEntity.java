package com.mysite.sbb.WebCrawler;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMPETITION")
public class WebCrawlerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String title;
    private String date;
    private int view;
    public WebCrawlerEntity() {
    }
    
    public WebCrawlerEntity(String imageUrl, String title, String date, int view) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.date = date;
        this.view = view;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}