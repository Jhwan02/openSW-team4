package com.mysite.sbb.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
	@RequiredArgsConstructor
	@Service
	public class NoticeService {
		private final NoticeRepository noticeRepository;

	    public List<Notice> getList() {
	        return this.noticeRepository.findAll();
	    }
	    
	    public Notice getQuestion(Integer id) {  
	        Optional<Notice> question = this.noticeRepository.findById(id);
	        if (question.isPresent()) {
	            return question.get();
	        } else {
	            throw new DataNotFoundException("question not found");
	        }
	    }
	    
	    public Page<Notice> getList(int page) {
	    	List<Sort.Order> sorts = new ArrayList<>();
	        sorts.add(Sort.Order.desc("createDate"));
	    	Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
	        return this.noticeRepository.findAll(pageable);
	    }
	    public void create(String subject, String content) {
	    	Notice q = new Notice();
	        q.setSubject(subject);
	        q.setContent(content);
	        q.setCreateDate(LocalDateTime.now());
	        this.noticeRepository.save(q);
	    }
	    public List<Notice> searchBySubject(String keyword) {
	        return noticeRepository.findBySubjectContaining(keyword);
	    }

	    public List<Notice> getRecentNotices(int count) {
	        // findTop3ByOrderByCreateDateDesc() 사용
	        return noticeRepository.findTop3ByOrderByCreateDateDesc();
	    }
	}

