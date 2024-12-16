package com.mysite.sbb.Board.Notice.notice;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.Board.Notice.noticeAnswer.NoticeAnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {
private final NoticeService noticeService;
    
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Notice> paging = this.noticeService.getList(page);
        model.addAttribute("paging", paging);
        return "notice_list";
    }
    
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id,NoticeAnswerForm noticeAnswerForm) {
    	Notice question = noticeService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("noticeAnswerForm", new NoticeAnswerForm()); // recruitAnswerForm 초기화
        return "notice_detail";
    }


    
    @GetMapping("/create")
    public String questionCreate(NoticeForm noticeForm) {
        return "notice_form"; // recruit_form.html 렌더링
    }
    
    @PostMapping("/create")
    public String questionCreate(@Valid NoticeForm noticeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "notice_form";
        }
        this.noticeService.create(noticeForm.getSubject(), noticeForm.getContent());
        return "redirect:/notice/list";
    }
    
}
    
    /*
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
    }*/