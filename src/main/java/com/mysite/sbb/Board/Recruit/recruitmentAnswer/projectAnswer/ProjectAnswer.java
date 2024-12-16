package com.mysite.sbb.Board.Recruit.recruitmentAnswer.projectAnswer;

import java.time.LocalDateTime;

import com.mysite.sbb.Board.Recruit.recruitmentQuestion.project.ProjectQuestion;
import com.mysite.sbb.login.User;

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
public class ProjectAnswer {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(columnDefinition = "TEXT")
	    private String content;

	    private LocalDateTime createDate;

	    @ManyToOne
	    private ProjectQuestion question; 
	    
	    @ManyToOne
	    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "id") // USER_TABLE 참조 확인
	    private User author;
	    
}
