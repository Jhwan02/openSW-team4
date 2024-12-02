package com.mysite.sbb.recruitmentAnswer;

import java.time.LocalDateTime;

import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestion; // Import 추가
import com.mysite.sbb.login.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecruitmentAnswer { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private RecruitmentQuestion question; 
    
    @ManyToOne
    private User author;
}
