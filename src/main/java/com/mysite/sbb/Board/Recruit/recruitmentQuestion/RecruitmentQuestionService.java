package com.mysite.sbb.Board.Recruit.recruitmentQuestion;

import com.mysite.sbb.DataNotFoundException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import com.mysite.sbb.login.User;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecruitmentQuestionService {

    private final RecruitmentQuestionRepository recruitmentQuestionRepository;

    // 전체 질문 목록 조회
    public List<RecruitmentQuestion> getList() {
        return this.recruitmentQuestionRepository.findAll();
    }

    // 단일 질문 조회
    public RecruitmentQuestion getQuestion(Integer id) {
        Optional<RecruitmentQuestion> question = this.recruitmentQuestionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("Recruitment question not found");
        }
    }

    // 페이징된 질문 목록 조회
    public Page<RecruitmentQuestion> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.recruitmentQuestionRepository.findAll(pageable);
    }

    // 질문 생성 (이미지 없이)
    public RecruitmentQuestion create(String subject, String content, String category, User author) {
        return create(subject, content, category, null, author); // 이미지 없이 생성
    }

    // 질문 생성 (이미지 포함)
    public RecruitmentQuestion create(String subject, String content, String category, String imageUrl, User author) {
        RecruitmentQuestion q = new RecruitmentQuestion();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setCategory(category);
        q.setAuthor(author);

        if (imageUrl != null) {
            q.setImageUrl(imageUrl); // 이미지 URL 설정
        }

        return this.save(q); // 저장 후 반환
    }

    // 질문 저장
    public RecruitmentQuestion save(RecruitmentQuestion question) {
        return this.recruitmentQuestionRepository.save(question);
    }

    // 제목으로 질문 검색
    public List<RecruitmentQuestion> searchBySubject(String keyword) {
        return recruitmentQuestionRepository.findBySubjectLike("%" + keyword + "%");
    }
    
    public String formatDateTime(LocalDateTime createDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(createDate, now);

        if (duration.toMinutes() == 0) {
            // 작성시간이 현재 시각과 동일한 분 -> "방금"
            return "방금";
        } else if (duration.toMinutes() < 60) {
            // 1시간 이내 -> "X분 전"
            return duration.toMinutes() + "분 전";
        } else if (createDate.toLocalDate().equals(now.toLocalDate())) {
            // 오늘 작성된 글 -> "HH:mm"
            return createDate.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            // 오늘 이전 작성된 글 -> "MM.dd"
            return createDate.format(DateTimeFormatter.ofPattern("MM/dd"));
        }
    }

    //마이페이지 필요
    // 작성자의 글만 가져오기
    public Page<RecruitmentQuestion> getListByAuthor(int page, String author) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.recruitmentQuestionRepository.findByAuthorUsername(author, pageable);
    }
}
