package com.mysite.sbb.Board.Recruit.recruitmentQuestion.project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.login.User;

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

 // 질문 생성 (이미지 없이)
    public ProjectQuestion create(String subject, String content, User author) {
        return create(subject, content, null, author); // 이미지 없이 생성
    }

    // 질문 생성 (이미지 포함)
    public ProjectQuestion create(String subject, String content, String imageUrl, User author) {
        ProjectQuestion q = new ProjectQuestion();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(author);
        if (imageUrl != null) {
            q.setImageUrl(imageUrl); // 이미지 URL 설정
        }

        return this.save(q); // 질문 저장 후 반환
    }
    
    public ProjectQuestion save(ProjectQuestion question) {
        return this.projectQuestionRepository.save(question);
    }

    // 제목으로 검색
    public List<ProjectQuestion> searchBySubject(String keyword) {
        return projectQuestionRepository.findBySubjectContaining(keyword);
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
}
