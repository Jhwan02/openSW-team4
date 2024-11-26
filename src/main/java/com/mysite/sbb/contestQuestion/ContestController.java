package com.mysite.sbb.contestQuestion;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.contestAnswer.ContestAnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/contest")
@RequiredArgsConstructor
@Controller
public class ContestController {

    private final ContestService contestService;

    // 질문 목록 표시
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<ContestQuestion> paging = this.contestService.getList(page);
        model.addAttribute("paging", paging);
        return "contest_list"; // question_list.html 렌더링
    }

    // 질문 상세 보기
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, ContestAnswerForm answerForm) {
        ContestQuestion question = this.contestService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("answerForm", new ContestAnswerForm());
        return "contest_detail";
    }
    // 질문 생성 폼
    @GetMapping("/create")
    public String questionCreate(ContestForm contestForm) {
        return "contest_form"; 
    }

    // 질문 생성 처리
    @PostMapping("/create")
    public String questionCreate(@Valid ContestForm contestForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contest_form"; // 유효성 검사 실패 시 폼 재표시
        }
        this.contestService.create(contestForm.getSubject(), contestForm.getContent());
        return "redirect:/contest/list"; // 질문 목록으로 리다이렉트
    }
}