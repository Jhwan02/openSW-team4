package com.mysite.sbb.techInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface blogPostRepository extends JpaRepository<blogPost, Long> {
    Optional<blogPost> findByTitleAndDate(String title, String date);
}
