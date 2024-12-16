package com.mysite.sbb.Board.Tech.techInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.data.domain.Sort;


import java.util.List;

@Controller
public class blogPostController {

    @Autowired
    private blogPostRepository blogPostRepository;

    @GetMapping("/techInfo")
    public String getBlogPosts(
            @RequestParam(value = "page", defaultValue = "1") int page, // 페이지 번호 추가
            Model model
    ) {
        int pageSize = 10; // 한 페이지에 표시할 데이터 수
        Pageable pageable = PageRequest.of(page - 1, pageSize); // 페이징 설정
        Page<blogPost> blogPostsPage = blogPostRepository.findAllByOrderByDateDesc(pageable); // 페이징 처리된 데이터 가져오기

        model.addAttribute("blogPostsPage", blogPostsPage); // 페이징 데이터 전달
        model.addAttribute("blogPosts", blogPostsPage.getContent()); // 현재 페이지의 데이터만 전달
        return "techInfo"; // techInfo.html로 이동
    }

   @GetMapping("/api/search")
    @ResponseBody
    public List<blogPost> searchBlogPosts(
            @RequestParam("keyword") String keyword
            //@PageableDefault(size = 10, sort = "date", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return blogPostRepository.findByTitleContainingIgnoreCaseOrderByDateDesc(keyword);
    }
}
