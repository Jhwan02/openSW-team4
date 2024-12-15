package com.mysite.sbb.recruitmentAnswer;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysite.sbb.login.User;
import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecruitmentAnswerService {

    private final RecruitmentAnswerRepository recruitmentAnswerRepository;


    public void create(RecruitmentQuestion question, String content, User author) {
        RecruitmentAnswer answer = new RecruitmentAnswer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.recruitmentAnswerRepository.save(answer);
    }
    
    //마이페이지 필요
    // 작성자의 글만 가져오기
    public Page<RecruitmentAnswer> getListByAuthor(int page, String author) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.recruitmentAnswerRepository.findByAuthorUsername(author, pageable);
    }
}
