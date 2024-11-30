package com.mysite.sbb.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeRepository noticeRepository;

    // 공지사항 목록 페이지
    @GetMapping("/notice")
    public String listNotices(Model model) {
        List<Notice> notices = noticeRepository.findAll();
        model.addAttribute("notices", notices);
        return "notice"; // notice.html 렌더링
    }

    // 공지사항 상세 페이지
    @GetMapping("/notice/{id}")
    public String detailNotice(@PathVariable Long id, Model model) {
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("공지사항을 찾을 수 없습니다."));
        model.addAttribute("notice", notice);
        return "notice_detail"; // notice_detail.html 렌더링
    }
}
