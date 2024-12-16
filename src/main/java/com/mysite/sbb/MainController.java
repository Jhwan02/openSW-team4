package com.mysite.sbb;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.sbb.Board.Notice.notice.Notice;
import com.mysite.sbb.Board.Notice.notice.NoticeService;
import com.mysite.sbb.Board.Tech.techInfo.blogPost;
import com.mysite.sbb.Board.Tech.techInfo.blogPostRepository;

@Controller
public class MainController {

    private final NoticeService noticeService;
    private final blogPostRepository blogPostRepository; // blogPostRepository 선언

    // 생성자에서 NoticeService와 blogPostRepository를 모두 주입
    public MainController(NoticeService noticeService, blogPostRepository blogPostRepository) {
        this.noticeService = noticeService;
        this.blogPostRepository = blogPostRepository; // blogPostRepository 초기화
    }

    @GetMapping("/")
    public String root(Model model) {
        // 최신 3개 공지 가져오기
        List<Notice> question = this.noticeService.getRecentNotices(3);
        model.addAttribute("question", question);

        // 최신 IT 뉴스 3개 가져오기
        List<blogPost> latestNews = this.blogPostRepository.findTop3ByOrderByDateDesc();
        model.addAttribute("latestNews", latestNews);

        return "main_page"; // main_page.html 렌더링
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "mypage/mypage"; // mypage.html 반환
    }
}
