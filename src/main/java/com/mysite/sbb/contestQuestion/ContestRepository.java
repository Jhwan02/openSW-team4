package com.mysite.sbb.contestQuestion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContestRepository extends JpaRepository <ContestQuestion, Integer>{
	
	ContestRepository findBySubject(String subject);
	ContestRepository findBySubjectAndContent(String subject, String content);
    List<ContestQuestion> findBySubjectLike(String subject);
    Page<ContestQuestion> findAll(Pageable pageable);
}

