package com.mysite.sbb.Board.Recruit.recruitmentAnswer.projectAnswer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.Board.Recruit.recruitmentQuestion.project.ProjectQuestion;
import com.mysite.sbb.Board.Recruit.recruitmentQuestion.project.ProjectQuestionService;
import com.mysite.sbb.login.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/recruit/project/answer")
@RequiredArgsConstructor
@Controller
public class ProjectAnswerController {

    private final ProjectQuestionService projectQuestionService;
    private final ProjectAnswerService projectAnswerService;

    // GET 요청이 들어오는 경우에 대한 처리
    @GetMapping("/create/{id}")
    public String createAnswerForm(@PathVariable("id") Integer id, Model model) {
        ProjectQuestion question = this.projectQuestionService.getQuestion(id);
        model.addAttribute("question", question);
        return "project_detail"; // 적절한 뷰를 반환
    }
    
    @PostMapping("/create/{id}")
    public String createAnswer(
            Model model,
            @PathVariable("id") Integer id,
            @Valid ProjectAnswerForm answerForm,
            BindingResult bindingResult,
            HttpSession session) {

        // 세션에서 사용자 정보 확인
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/auth/login";
        }

        ProjectQuestion question = this.projectQuestionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "project_detail"; // 유효성 검사 실패 시 질문 상세 페이지 재표시
        }

        // 답변 생성 로직
        this.projectAnswerService.create(question, answerForm.getContent(), user);

        // 성공적으로 답변 생성 후 질문 상세 페이지로 리다이렉트
        return String.format("redirect:/recruit/project/detail/%s", id);
    }
    }