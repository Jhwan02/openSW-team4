package com.mysite.sbb.contestAnswer;
import java.time.LocalDateTime;

import com.mysite.sbb.contestQuestion.ContestQuestion; // Import 추가
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
public class ContestAnswer { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private ContestQuestion contestQuestion; 
    
    @ManyToOne
    private User author;
    
}
