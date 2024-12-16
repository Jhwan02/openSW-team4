package com.mysite.sbb.Board.Recruit.recruitmentQuestion.project;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProjectQuestionRepository extends JpaRepository <ProjectQuestion, Integer>{
	
	ProjectQuestionRepository findBySubject(String subject);
	ProjectQuestionRepository findBySubjectAndContent(String subject, String content);
    List<ProjectQuestion> findBySubjectLike(String subject);
    Page<ProjectQuestion> findAll(Pageable pageable);
    List<ProjectQuestion> findBySubjectContaining(String keyword);
}
