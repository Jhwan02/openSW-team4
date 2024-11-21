package com.mysite.sbb.recruitmentQuestion;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class RecruitmentQuestionController {

    private final RecruitmentQuestionService questionService;
    

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<RecruitmentQuestion> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "recruit_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        RecruitmentQuestion question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "recruit_detail";
    }
    @GetMapping("/create")
    public String questionCreate(Model model) {
        model.addAttribute("recruitForm", new RecruitmentQuestionForm());
        return "recruit_form"; // recruit_form.html 렌더링
    }

    @PostMapping("/create")
    public String questionCreate(@Valid RecruitmentQuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recruit_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/recruit/list";
    }
}
