package com.mysite.sbb.Board.Free.answer;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysite.sbb.Board.Free.question.Question;
import com.mysite.sbb.login.User;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public void create(Question question, String content,User author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }

    //마이페이지 필요
    // 작성자의 글만 가져오기
    public Page<Answer> getListByAuthor(int page, String author) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.answerRepository.findByAuthorUsername(author, pageable);
    }
}
