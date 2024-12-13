package com.mysite.sbb.noticeAnswer;


import com.mysite.sbb.notice.Notice;
import com.mysite.sbb.notice.NoticeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@RequestMapping("/notice/answer")
@RequiredArgsConstructor
@Controller
public class NoticeAnswerController {

    private final NoticeService noticeService;
    private final NoticeAnswerService noticeAnswerService;

    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable("id") Integer id,
                               @Valid NoticeAnswerForm noticeAnswerForm,
                               BindingResult bindingResult,
                               Model model) {
        Notice question = noticeService.getQuestion(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question); // question 추가
            return "notice_detail";
        }

        noticeAnswerService.create(question, noticeAnswerForm.getContent());
        return String.format("redirect:/notice/detail/%d", id);

    }
    }