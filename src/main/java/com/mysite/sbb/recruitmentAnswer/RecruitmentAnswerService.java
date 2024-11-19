package com.mysite.sbb.recruitmentAnswer;

import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RecruitmentAnswerService {

    private final RecruitmentAnswerRepository recruitmentAnswerRepository;


    public void create(RecruitmentQuestion question, String content) {
        RecruitmentAnswer answer = new RecruitmentAnswer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.recruitmentAnswerRepository.save(answer);
    }
}
