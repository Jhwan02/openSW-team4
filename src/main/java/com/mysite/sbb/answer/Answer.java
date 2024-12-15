package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import com.mysite.sbb.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysite.sbb.login.User;
@Getter
@Setter
@Entity

public class Answer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	 @Column(columnDefinition = "TEXT")
	 private String content;

	 private LocalDateTime createDate; 
	 
	 @ManyToOne
	 @JsonIgnore
	 private Question question;  
	 
	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "id") // USER_TABLE 참조 확인
	private User author;
	    
	 

}
