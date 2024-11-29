package com.mysite.sbb.contestAnswer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import java.security.Principal;

import com.mysite.sbb.contestQuestion.ContestQuestion;
import com.mysite.sbb.contestQuestion.ContestService;
import com.mysite.sbb.login.User;
import com.mysite.sbb.login.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/contest/answer")
@RequiredArgsConstructor
@Controller
public class ContestAnswerController {

    private final ContestService contestService;
    private final ContestAnswerService answerService;
    private final UserService userService;
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, 
            @Valid ContestAnswerForm contestAnswerForm, BindingResult bindingResult,Principal principal) {
        ContestQuestion question = this.contestService.getQuestion(id);
        User siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "contest_detail";
        }
        this.answerService.create(question, contestAnswerForm.getContent(),siteUser);
        return String.format("redirect:/contest/detail/%s", id);
    }

    

}

