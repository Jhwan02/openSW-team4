package com.mysite.sbb.recruitmentQuestion;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.login.User;
public interface RecruitmentQuestionRepository extends JpaRepository <RecruitmentQuestion, Integer>{
	
	RecruitmentQuestionRepository findBySubject(String subject);
	RecruitmentQuestionRepository findBySubjectAndContent(String subject, String content);
    List<RecruitmentQuestion> findBySubjectLike(String subject);
    Page<RecruitmentQuestion> findAll(Pageable pageable);
    Page<RecruitmentQuestion> findByAuthorUsername(String author, Pageable pageable); //유저이름 기준 글 찾기
}

