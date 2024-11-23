package com.mysite.sbb.recruitmentAnswer.projectAnswer;

import com.mysite.sbb.recruitmentQuestion.project.ProjectQuestion;
import com.mysite.sbb.recruitmentQuestion.project.ProjectQuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@RequestMapping("/recruit/project/answer")
@RequiredArgsConstructor
@Controller
public class ProjectAnswerController {

    private final ProjectQuestionService projectQuestionService;
    private final ProjectAnswerService projectAnswerService;

    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable("id") Integer id,
                               @Valid ProjectAnswerForm projectAnswerForm,
                               BindingResult bindingResult,
                               Model model) {
        ProjectQuestion question = projectQuestionService.getQuestion(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question); // question 추가
            return "project_detail";
        }

        projectAnswerService.create(question, projectAnswerForm.getContent());
        return String.format("redirect:/recruit/project/detail/%d", id);

    }
    }