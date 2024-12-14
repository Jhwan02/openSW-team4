package com.mysite.sbb.contestAnswer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.contestQuestion.ContestQuestion;
import com.mysite.sbb.contestQuestion.ContestService;
import com.mysite.sbb.login.User;
// import com.mysite.sbb.question.Question;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/contest/answer")
@RequiredArgsConstructor
@Controller
public class ContestAnswerController {

    private final ContestService contestService;
    private final ContestAnswerService answerService;
   
 // GET 요청이 들어오는 경우에 대한 처리
    @GetMapping("/create/{id}")
    public String createAnswerForm(@PathVariable("id") Integer id, Model model) {
        ContestQuestion question = this.contestService.getQuestion(id);
        model.addAttribute("question", question);
        return "contest_detail"; // 적절한 뷰를 반환
    }
    
    @PostMapping("/create/{id}")
    public String createAnswer(
            Model model,
            @PathVariable("id") Integer id,
            @Valid ContestAnswerForm answerForm,
            BindingResult bindingResult,
            HttpSession session) {

        // 세션에서 사용자 정보 확인
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/auth/login";
        }

        ContestQuestion question = this.contestService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "contest_detail"; // 유효성 검사 실패 시 질문 상세 페이지 재표시
        }

        // 답변 생성 로직
        this.answerService.create(question, answerForm.getContent(), user);

        // 성공적으로 답변 생성 후 질문 상세 페이지로 리다이렉트
        return String.format("redirect:/contest/detail/%s", id);
    }
    

}

