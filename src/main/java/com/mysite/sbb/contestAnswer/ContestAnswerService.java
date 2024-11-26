package com.mysite.sbb.contestAnswer;

import com.mysite.sbb.contestQuestion.ContestQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
