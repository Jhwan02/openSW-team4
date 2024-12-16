package com.mysite.sbb.Board.Recruit.recruitmentAnswer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecruitmentAnswerRepository extends JpaRepository <RecruitmentAnswer, Integer> {
    Page<RecruitmentAnswer> findByAuthorUsername(String author, Pageable pageable); //유저이름 기준 글 찾기
}
