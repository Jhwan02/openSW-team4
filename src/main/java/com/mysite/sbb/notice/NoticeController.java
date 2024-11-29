package com.mysite.sbb.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeRepository noticeRepository;

    //공지사항 목록 페이지
    @GetMapping("/notice")
    public String listNotices(Model model) {
        List<Notice> notices = noticeRepository.findAll();
        model.addAttribute("notices", notices);
        return "notice";
    }

    //공지사항 상세 페이지
    @GetMapping("/notice/{id}")
    public String detailNotice(@PathVariable Integer id, Model model){
        Notice notice = noticeRepository.findByID(id).or ElseThrow(()->new RuntimeException("공지사항을 찾을 수 없습니다."));
        model.addAttribute("notice", notice);
        return "notice_detail";
    }
}