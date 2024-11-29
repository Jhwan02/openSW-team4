package com.mysite.sbb.recruitmentAnswer.projectAnswer;

import com.mysite.sbb.recruitmentQuestion.project.ProjectQuestion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProjectAnswerService {

    private final ProjectAnswerRepository projectAnswerRepository;


    public void create(ProjectQuestion question, String content) {
        ProjectAnswer answer = new ProjectAnswer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.projectAnswerRepository.save(answer);
    }
}
