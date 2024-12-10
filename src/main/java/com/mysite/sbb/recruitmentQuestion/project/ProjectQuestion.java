package com.mysite.sbb.recruitmentQuestion.project;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.login.User;
import com.mysite.sbb.recruitmentAnswer.projectAnswer.ProjectAnswer; // Import 추가


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PROJECT_QUESTION")
public class ProjectQuestion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)  // 필드명 일치
    private List<ProjectAnswer> answerList;
    
    private String imageUrl;
   
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
    private User author;

}
