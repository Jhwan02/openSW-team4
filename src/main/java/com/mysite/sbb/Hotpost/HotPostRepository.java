package com.mysite.sbb.Hotpost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HotPostRepository extends JpaRepository<HotPost, Long> {

    // 최신순 정렬
    List<HotPost> findAllByOrderByDateDesc();

    // 제목 검색
    List<HotPost> findByTitleContainingIgnoreCase(String keyword);
}
