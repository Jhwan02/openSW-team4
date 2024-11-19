	package com.mysite.sbb.recruitmentAnswer;
	
	import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestion;
	import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestionService;
	
	import lombok.RequiredArgsConstructor;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import jakarta.validation.Valid;
	import org.springframework.validation.BindingResult;
	@Controller
	@RequiredArgsConstructor
	@RequestMapping("/recruit/answer")
	public class RecruitmentAnswerController {
	
	    private final RecruitmentQuestionService recruitmentQuestionService;
	    private final RecruitmentAnswerService recruitmentAnswerService;
	
	    @PostMapping("/create/{id}")
	    public String createAnswer(Model model, @PathVariable("id") Integer id, 
	            @Valid RecruitmentAnswerForm answerForm, BindingResult bindingResult) {
	        RecruitmentQuestion question = this.recruitmentQuestionService.getQuestion(id);
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("question", question);
	            return "recruit_detail";
	        }
	        this.recruitmentAnswerService.create(question, answerForm.getContent());
	        return String.format("redirect:/recruit/detail/%s", id);
	    }
	}
	
