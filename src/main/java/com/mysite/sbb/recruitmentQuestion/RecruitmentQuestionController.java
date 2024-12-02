package com.mysite.sbb.recruitmentQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbb.recruitmentAnswer.RecruitmentAnswerForm;
import java.security.Principal;
import com.mysite.sbb.login.User;
import com.mysite.sbb.login.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/recruit")
@RequiredArgsConstructor
@Controller
public class RecruitmentQuestionController {
private final RecruitmentQuestionService recruitQuestionService;
private final UserService userService;

    
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
    public String questionCreate(@Valid RecruitmentQuestionForm recruitQuestionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "recruit_form";
        }
        User siteUser = this.userService.getUser(principal.getName());
        this.recruitQuestionService.create(recruitQuestionForm.getSubject(), recruitQuestionForm.getContent(),recruitQuestionForm.getCategory(),siteUser);
        return "redirect:/recruit/list";
        
    }
    
    @GetMapping("/api/search")
    @ResponseBody
    public List<Map<String, Object>> searchRecruitmentQuestions(@RequestParam("keyword") String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<RecruitmentQuestion> questions = recruitQuestionService.searchBySubject(keyword);
        List<Map<String, Object>> results = new ArrayList<>();
        for (RecruitmentQuestion question : questions) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", question.getId());
            map.put("subject", question.getSubject());
            results.add(map);
        }
    return results;
    }
}
