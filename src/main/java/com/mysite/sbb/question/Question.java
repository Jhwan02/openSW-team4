package com.mysite.sbb.question;

import java.time.LocalDateTime;

import java.util.List;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.CascadeType; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany; 
import jakarta.persistence.ManyToOne;
import com.mysite.sbb.login.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    private String imageUrl; // 이미지 URL 저장 필드
    
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
    private User author;



    private Integer likes = 0; // 좋아요 수 필드 (초기값 0)
    
}

