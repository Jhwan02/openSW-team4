package com.mysite.sbb.WebCrawler;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String title;
    private String date;

    // Default constructor for JPA
    public Competition() {
    }

    // Constructor for creating a new instance
    public Competition(String imageUrl, String title, String date) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.date = date;
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
}
