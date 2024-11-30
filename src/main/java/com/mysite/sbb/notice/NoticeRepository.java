package com.mysite.sbb.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NoticeRepository extends JpaRepository <Notice, Integer>{
	
	NoticeRepository findBySubject(String subject);
	NoticeRepository findBySubjectAndContent(String subject, String content);
    List<Notice> findBySubjectLike(String subject);
    Page<Notice> findAll(Pageable pageable);
    List<Notice> findBySubjectContaining(String keyword);
    List<Notice> findTop3ByOrderByCreateDateDesc(); // 최신 3개를 반환
		
	
}

