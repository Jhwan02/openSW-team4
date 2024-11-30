package com.mysite.sbb;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.sbb.notice.Notice;
import com.mysite.sbb.notice.NoticeService;

@Controller
public class MainController {
	
	private final NoticeService noticeService;
	
	public MainController(NoticeService noticeService) {
        this.noticeService = noticeService;
	}

    @GetMapping("/")
    public String root(Model model) {
        List<Notice> question = this.noticeService.getRecentNotices(3); // 최신 3개 공지 가져오기
        model.addAttribute("question", question);
        return "main_page"; // main_page.html 렌더링
    }
	
  @GetMapping("/mypage")
  public String myPage() {
      return "mypage/mypage"; // mypage.html을 반환
  }
  
}