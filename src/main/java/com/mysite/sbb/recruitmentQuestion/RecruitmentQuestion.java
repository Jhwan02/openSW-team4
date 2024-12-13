package com.mysite.sbb.recruitmentQuestion;

import java.time.LocalDateTime;

import java.util.List;

import com.mysite.sbb.login.User;
import com.mysite.sbb.recruitmentAnswer.RecruitmentAnswer; // Import 추가

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecruitmentQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)  // 필드명 일치
    private List<RecruitmentAnswer> answerList;
    
    private String category; /*카테고리값 저장컬럼*/
    
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
    private User author;
    
    private String imageUrl; // 이미지 URL 저장 필드
}
