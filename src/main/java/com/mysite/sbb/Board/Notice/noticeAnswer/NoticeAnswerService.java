package com.mysite.sbb.Board.Notice.noticeAnswer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mysite.sbb.Board.Notice.notice.Notice;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NoticeAnswerService {

    private final NoticeAnswerRepository noticeAnswerRepository;


    public void create(Notice question, String content) {
        NoticeAnswer answer = new NoticeAnswer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.noticeAnswerRepository.save(answer);
    }
}
