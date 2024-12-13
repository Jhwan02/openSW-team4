package com.mysite.sbb.contestAnswer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.sbb.contestQuestion.ContestQuestion;
import com.mysite.sbb.login.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContestAnswerService {

    private final ContestAnswerRepository contestAnswerRepository;


    public void create(ContestQuestion question, String content, User author) {
        ContestAnswer answer = new ContestAnswer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setContestQuestion(question);
        answer.setAuthor(author);
        this.contestAnswerRepository.save(answer);
    }
}
