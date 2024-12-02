package com.mysite.sbb.contestAnswer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.sbb.contestQuestion.ContestQuestion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContestAnswerService {

    private final ContestAnswerRepository contestAnswerRepository;


    public void create(ContestQuestion question, String content) {
        ContestAnswer answer = new ContestAnswer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setContestQuestion(question);
        this.contestAnswerRepository.save(answer);
    }
}
