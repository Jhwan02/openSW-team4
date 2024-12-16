package com.mysite.sbb.Board.Recruit.recruitmentAnswer;
	
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.Board.Recruit.recruitmentQuestion.RecruitmentQuestion;
import com.mysite.sbb.Board.Recruit.recruitmentQuestion.RecruitmentQuestionService;
//import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.login.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
	
	@RequestMapping("/recruit/answer")
	@RequiredArgsConstructor
	@Controller
	public class RecruitmentAnswerController {
	
	    private final RecruitmentQuestionService recruitmentQuestionService;
	    private final RecruitmentAnswerService recruitmentAnswerService;

	    // GET 요청이 들어오는 경우에 대한 처리
	    @GetMapping("/create/{id}")
	    public String createAnswerForm(@PathVariable("id") Integer id, Model model) {
	        RecruitmentQuestion question = this.recruitmentQuestionService.getQuestion(id);
	        model.addAttribute("question", question);
	        return "recruit_detail"; // 적절한 뷰를 반환
	    }
	    
	    @PostMapping("/create/{id}")
	    public String createAnswer(
	            Model model,
	            @PathVariable("id") Integer id,
	            @Valid RecruitmentAnswerForm answerForm,
	            BindingResult bindingResult,
	            HttpSession session) {

	        // 세션에서 사용자 정보 확인
	        User user = (User) session.getAttribute("user");
	        if (user == null) {
	            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
	            return "redirect:/auth/login";
	        }

	        RecruitmentQuestion question = this.recruitmentQuestionService.getQuestion(id);
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("question", question);
	            return "recruit_detail"; // 유효성 검사 실패 시 질문 상세 페이지 재표시
	        }

	        // 답변 생성 로직
	        this.recruitmentAnswerService.create(question, answerForm.getContent(), user);

	        // 성공적으로 답변 생성 후 질문 상세 페이지로 리다이렉트
	        return String.format("redirect:/recruit/detail/%s", id);
	    }
	}
	    
	
	
