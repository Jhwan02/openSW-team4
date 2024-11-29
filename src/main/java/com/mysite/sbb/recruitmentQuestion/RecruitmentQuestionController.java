package com.mysite.sbb.recruitmentQuestion;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.recruitmentAnswer.RecruitmentAnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/recruit")
@RequiredArgsConstructor
@Controller
public class RecruitmentQuestionController {
private final RecruitmentQuestionService recruitQuestionService;
    
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<RecruitmentQuestion> paging = this.recruitQuestionService.getList(page);
        model.addAttribute("paging", paging);
        return "recruit_list";
    }
    
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, RecruitmentAnswerForm recruitAnswerForm) {
    	RecruitmentQuestion question = recruitQuestionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("recruitAnswerForm", new RecruitmentAnswerForm()); // recruitAnswerForm 초기화
        return "recruit_detail";
    }


    @PreAuthorize("isAuthenticated()") //추가
    @GetMapping("/create")
    public String questionCreate(RecruitmentQuestionForm recruitmentQuestionForm) {
        return "recruit_form"; // recruit_form.html 렌더링
    }
    
    @PreAuthorize("isAuthenticated()") //추가
    @PostMapping("/create")
    public String questionCreate(@Valid RecruitmentQuestionForm recruitQuestionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recruit_form";
        }
        this.recruitQuestionService.create(recruitQuestionForm.getSubject(), recruitQuestionForm.getContent(),recruitQuestionForm.getCategory());
        return "redirect:/recruit/list";
        
    }
    
}

