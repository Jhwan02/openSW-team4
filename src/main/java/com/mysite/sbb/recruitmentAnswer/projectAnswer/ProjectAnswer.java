package com.mysite.sbb.recruitmentAnswer.projectAnswer;

import java.time.LocalDateTime;

import com.mysite.sbb.recruitmentQuestion.project.ProjectQuestion; // Import 추가

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
public class ProjectAnswer {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(columnDefinition = "TEXT")
	    private String content;

	    private LocalDateTime createDate;

	    @ManyToOne
	    private ProjectQuestion question; 
}
