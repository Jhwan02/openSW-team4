package com.mysite.sbb.Board.Recruit.recruitmentAnswer;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysite.sbb.Board.Recruit.recruitmentQuestion.RecruitmentQuestion;
import com.mysite.sbb.login.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JsonIgnore
    private RecruitmentQuestion question; 
    
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "id") // USER_TABLE 참조 확인
    private User author;
    
}
