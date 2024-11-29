package com.mysite.sbb.recruitmentQuestion.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbb.recruitmentAnswer.projectAnswer.ProjectAnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/recruit/project")
@RequiredArgsConstructor
@Controller
public class ProjectQuestionController {
private final ProjectQuestionService projectQuestionService;
    
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<ProjectQuestion> paging = this.projectQuestionService.getList(page);
        model.addAttribute("paging", paging);
        return "project_list";
    }
    
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id,ProjectAnswerForm recruitAnswerForm) {
    	ProjectQuestion question = projectQuestionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("recruitAnswerForm", new ProjectAnswerForm()); // recruitAnswerForm 초기화
        return "project_detail";
    }


    
    @GetMapping("/create")
    public String questionCreate(ProjectQuestionForm projectQuestionForm) {
        return "project_form"; // recruit_form.html 렌더링
    }
    
    @PostMapping("/create")
    public String questionCreate(@Valid ProjectQuestionForm projectQuestionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "project_form";
        }
        this.projectQuestionService.create(projectQuestionForm.getSubject(), projectQuestionForm.getContent());
        return "redirect:/recruit/project/list";
    }
    @GetMapping("/api/search")
    @ResponseBody
    public List<Map<String, Object>> searchProjectQuestions(@RequestParam("keyword") String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<ProjectQuestion> questions = projectQuestionService.searchBySubject(keyword);
        List<Map<String, Object>> results = new ArrayList<>();
        for (ProjectQuestion question : questions) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", question.getId());
            map.put("subject", question.getSubject());
            results.add(map);
        }
        return results;
    }
}
