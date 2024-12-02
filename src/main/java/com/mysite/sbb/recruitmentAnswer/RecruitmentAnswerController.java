	package com.mysite.sbb.recruitmentAnswer;
	
	import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestion;
	import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestionService;
	import com.mysite.sbb.login.User;
	import com.mysite.sbb.login.UserService;
	import org.springframework.security.access.prepost.PreAuthorize;
	import java.security.Principal;

	import lombok.RequiredArgsConstructor;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import jakarta.validation.Valid;
	import org.springframework.validation.BindingResult;
	
	@RequestMapping("/recruit/answer")
	@RequiredArgsConstructor
	@Controller
	public class RecruitmentAnswerController {
	
	    private final RecruitmentQuestionService recruitmentQuestionService;
	    private final RecruitmentAnswerService recruitmentAnswerService;
	    private final UserService userService;
	    
	    @PreAuthorize("isAuthenticated()")
	    @PostMapping("/create/{id}")
	    public String createAnswer(@PathVariable("id") Integer id,
	                               @Valid RecruitmentAnswerForm recruitAnswerForm,
	                               BindingResult bindingResult,Principal principal,
	                               Model model) {
	        RecruitmentQuestion question = recruitmentQuestionService.getQuestion(id);
	        User siteUser = this.userService.getUser(principal.getName());
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("question", question); // question 추가
	            return "recruit_detail";
	        }

	        recruitmentAnswerService.create(question, recruitAnswerForm.getContent(),siteUser);
	        return String.format("redirect:/recruit/detail/%d", id);

	    }
	}
	    
	
	
