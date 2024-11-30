package com.mysite.sbb.recruitmentAnswer;
	
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestion;
import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
	
	@RequestMapping("/recruit/answer")
	@RequiredArgsConstructor
	@Controller
	public class RecruitmentAnswerController {
	
	    private final RecruitmentQuestionService recruitmentQuestionService;
	    private final RecruitmentAnswerService recruitmentAnswerService;

	    @PostMapping("/create/{id}")
	    public String createAnswer(@PathVariable("id") Integer id,
	                               @Valid RecruitmentAnswerForm recruitAnswerForm,
	                               BindingResult bindingResult,Model model) {
	        RecruitmentQuestion question = recruitmentQuestionService.getQuestion(id);
	       
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("question", question); // question 추가
	            return "recruit_detail";
	        }

	        recruitmentAnswerService.create(question, recruitAnswerForm.getContent());
	        return String.format("redirect:/recruit/detail/%d", id);

	    }
	}
	    
	
	
