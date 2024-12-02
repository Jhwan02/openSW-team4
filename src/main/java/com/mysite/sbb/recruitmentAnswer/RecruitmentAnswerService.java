package com.mysite.sbb.recruitmentAnswer;

import com.mysite.sbb.login.User;
import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
