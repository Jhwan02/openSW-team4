package com.mysite.sbb.recruitmentQuestion;

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
public class RecruitmentQuestionService {

    private final RecruitmentQuestionRepository recruitmentQuestionRepository;

    public List<RecruitmentQuestion> getList() {
        return this.recruitmentQuestionRepository.findAll();
    }
    
    public RecruitmentQuestion getQuestion(Integer id) {  
        Optional<RecruitmentQuestion> question = this.recruitmentQuestionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    
    public Page<RecruitmentQuestion> getList(int page) {
    	List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
    	Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.recruitmentQuestionRepository.findAll(pageable);
    }
    public void create(String subject, String content, String category) {
        RecruitmentQuestion q = new RecruitmentQuestion();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setCategory(category);
        this.recruitmentQuestionRepository.save(q);
    }
}

