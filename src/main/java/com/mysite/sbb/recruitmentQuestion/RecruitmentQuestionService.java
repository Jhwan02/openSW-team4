package com.mysite.sbb.recruitmentQuestion;

import com.mysite.sbb.DataNotFoundException;
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
    public RecruitmentQuestion create(String subject, String content, String category) {
        return create(subject, content, category, null); // 이미지 없이 생성
    }

    // 질문 생성 (이미지 포함)
    public RecruitmentQuestion create(String subject, String content, String category, String imageUrl) {
        RecruitmentQuestion q = new RecruitmentQuestion();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setCategory(category);

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
}
