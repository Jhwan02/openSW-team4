package com.mysite.sbb.Board.Recruit.recruitmentAnswer.projectAnswer;

import com.mysite.sbb.Board.Recruit.recruitmentQuestion.project.ProjectQuestion;
import com.mysite.sbb.login.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProjectAnswerService {

    private final ProjectAnswerRepository projectAnswerRepository;


    public void create(ProjectQuestion question, String content, User author) {
        ProjectAnswer answer = new ProjectAnswer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.projectAnswerRepository.save(answer);
    }
}
