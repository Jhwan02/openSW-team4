package com.mysite.sbb.Hotpost;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotPostService {

    private final HotPostRepository hotPostRepository;

    public HotPostService(HotPostRepository hotPostRepository) {
        this.hotPostRepository = hotPostRepository;
    }

    public List<HotPost> getAllPosts() {
        return hotPostRepository.findAllByOrderByDateDesc();
    }

    public List<HotPost> searchPosts(String keyword) {
        return hotPostRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
