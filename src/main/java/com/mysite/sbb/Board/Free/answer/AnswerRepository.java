package com.mysite.sbb.Board.Free.answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Page<Answer> findByAuthorUsername(String author, Pageable pageable); //유저이름 기준 글 찾기
}
