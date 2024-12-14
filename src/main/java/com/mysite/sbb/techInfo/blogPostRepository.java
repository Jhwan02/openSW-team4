package com.mysite.sbb.techInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.List;


public interface blogPostRepository extends JpaRepository<blogPost, Long> {
    Optional<blogPost> findByTitle(String title); // title 기준 검색
    List<blogPost> findAllByOrderByDateDesc(); // 날짜 기준 내림차순 정렬
    List<blogPost> findTop3ByOrderByDateDesc(); // 최신 3개만 가져오기
    List<blogPost> findByTitleContainingIgnoreCase(String keyword); // 제목에 키워드 포함 여부 검색
    Page<blogPost> findAllByOrderByDateDesc(Pageable pageable); // 페이징 지원 메서드 추가
}