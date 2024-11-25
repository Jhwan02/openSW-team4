package com.mysite.sbb.techInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;


public interface blogPostRepository extends JpaRepository<blogPost, Long> {
    Optional<blogPost> findByTitle(String title); // title 기준 검색
    List<blogPost> findAllByOrderByDateDesc(); // 날짜 기준 내림차순 정렬
}
