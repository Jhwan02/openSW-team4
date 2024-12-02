package com.mysite.sbb.recruitmentQuestion.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProjectQuestionService {
    private final ProjectQuestionRepository projectQuestionRepository;

    // 전체 리스트 조회
    public List<ProjectQuestion> getList() {
        return this.projectQuestionRepository.findAll();
    }

    // 질문 조회
    public ProjectQuestion getQuestion(Integer id) {
        Optional<ProjectQuestion> question = this.projectQuestionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    // 페이지네이션을 사용하여 리스트 조회
    public Page<ProjectQuestion> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.projectQuestionRepository.findAll(pageable);
    }

    // 질문 생성 (이미지 URL 포함)
    public ProjectQuestion create(String subject, String content, String imageURL) {
        ProjectQuestion q = new ProjectQuestion();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setImageUrl(imageURL);  // 이미지 URL 설정
        return this.projectQuestionRepository.save(q);
    }

    // 질문 생성 (이미지 없이)
    public ProjectQuestion create(String subject, String content) {
        ProjectQuestion q = new ProjectQuestion();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        return this.projectQuestionRepository.save(q);
    }
    
    public ProjectQuestion save(ProjectQuestion question) {
        return this.projectQuestionRepository.save(question);
    }

    // 제목으로 검색
    public List<ProjectQuestion> searchBySubject(String keyword) {
        return projectQuestionRepository.findBySubjectContaining(keyword);
    }
}
