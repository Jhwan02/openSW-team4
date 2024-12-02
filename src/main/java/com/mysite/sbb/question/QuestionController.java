package com.mysite.sbb.question;

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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UploadController uploadController; // 이미지 업로드 컨트롤러 추가

    // 질문 목록 표시
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list"; // question_list.html 렌더링
    }

    // 질문 상세 보기
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail"; // question_detail.html 렌더링
    }

    // 질문 생성 폼
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form"; // question_form.html 렌더링
    }

    // 질문 생성 처리
    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "question_form"; // 유효성 검사 실패 시 폼 재표시
        }

        Question question;
        if (file != null && !file.isEmpty()) {
            try {
                UploadResultDTO uploadResult = uploadController.uploadFile(file);
                question = this.questionService.create(
                        questionForm.getSubject(),
                        questionForm.getContent(),
                        uploadResult.getImageURL()
                );
            } catch (Exception e) {
                model.addAttribute("uploadError", "이미지 업로드 중 문제가 발생했습니다.");
                return "question_form"; // 에러 발생 시 폼 재표시
            }
        } else {
            question = this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        }

        this.questionService.save(question);

        return "redirect:/question/list"; // 질문 목록으로 리다이렉트
    }

    // 제목으로 질문 검색 API
    @GetMapping("/api/search")
    @ResponseBody
    public List<Map<String, Object>> searchQuestions(@RequestParam("keyword") String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(); // 빈 리스트 반환
        }
        List<Question> questions = questionService.searchBySubject(keyword);
        List<Map<String, Object>> results = new ArrayList<>();
        for (Question question : questions) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", question.getId());
            map.put("subject", question.getSubject());
            results.add(map);
        }
        return results;
    }
}
