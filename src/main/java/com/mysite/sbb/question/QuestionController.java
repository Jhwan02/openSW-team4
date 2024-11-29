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
import org.springframework.web.multipart.MultipartFile;

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

        // 질문 생성 (이미지 없이 생성)
        Question question;
        if (file != null && !file.isEmpty()) {
            // 파일이 있으면 이미지 업로드 처리 후 질문 생성
            try {
                UploadResultDTO uploadResult = uploadController.uploadFile(file);
                question = this.questionService.create(questionForm.getSubject(), questionForm.getContent(), uploadResult.getImageURL());
            } catch (Exception e) {
                model.addAttribute("uploadError", "이미지 업로드 중 문제가 발생했습니다.");
                return "question_form"; // 에러 발생 시 폼 재표시
            }
        } else {
            // 파일이 없으면 그냥 질문 생성
            question = this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        }

        // 질문 저장
        this.questionService.save(question);

        return "redirect:/question/list"; // 질문 목록으로 리다이렉트
    }
}
