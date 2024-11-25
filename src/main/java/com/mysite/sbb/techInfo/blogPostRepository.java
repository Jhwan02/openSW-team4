package com.mysite.sbb.techInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface blogPostRepository extends JpaRepository<blogPost, Long> {
    Optional<blogPost> findByTitle(String title); // title 기준 검색
}
