package com.mysite.sbb.Board.Free.question;
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
import org.springframework.web.multipart.MultipartFile;

import com.mysite.sbb.Board.Free.answer.AnswerForm;
import com.mysite.sbb.login.User;
import com.mysite.sbb.upload.UploadController;
import com.mysite.sbb.upload.UploadResultDTO;

import jakarta.servlet.http.HttpSession;
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
        // 페이징된 질문 목록 가져오기
        Page<Question> paging = this.questionService.getList(page);

        // 작성시간 포맷팅 처리
        List<Map<String, Object>> formattedQuestions = new ArrayList<>();
        for (Question question : paging.getContent()) {
            Map<String, Object> formattedQuestion = new HashMap<>();
            formattedQuestion.put("id", question.getId());
            formattedQuestion.put("subject", question.getSubject());
            formattedQuestion.put("author", question.getAuthor() != null ? question.getAuthor().getUsername() : "작성자 없음");

            // createDate가 null일 경우 기본값 설정
            if (question.getCreateDate() != null) {
                formattedQuestion.put("formattedDate", questionService.formatDateTime(question.getCreateDate()));
            } else {
                formattedQuestion.put("formattedDate", "알 수 없음");
            }
            formattedQuestions.add(formattedQuestion);
        }

        // Model에 데이터 추가
        model.addAttribute("paging", paging);
        model.addAttribute("formattedQuestions", formattedQuestions);

        return "question_list"; // question_list.html 렌더링
    }
    
    // 질문 상세 보기
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail"; // question_detail.html 렌더링
    }
    
    @GetMapping("/create")
    public String questionCreate(HttpSession session, QuestionForm questionForm) {
        // 세션에서 사용자 정보 확인
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/auth/login";
        }
        // 로그인된 상태라면 질문 작성 폼으로 이동
        return "question_form";
    }

  
    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Model model,
            HttpSession session) {

        // 세션에서 사용자 정보 확인
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/auth/login";
        }

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
                        uploadResult.getImageURL(),
                        user
                );
            } catch (Exception e) {
                model.addAttribute("uploadError", "이미지 업로드 중 문제가 발생했습니다.");
                return "question_form"; // 에러 발생 시 폼 재표시
            }
        } else {
            question = this.questionService.create(questionForm.getSubject(), questionForm.getContent(), user);
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

    @PostMapping("/like/{id}")
    @ResponseBody
    public Map<String, Object> likeQuestion(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 좋아요 증가
            this.questionService.increaseLikes(id);
            Question question = this.questionService.getQuestion(id);

            // 성공적인 응답
            response.put("success", true);
            response.put("likes", question.getLikes());
        } catch (Exception e) {
            // 오류 처리
            response.put("success", false);
            response.put("message", "좋아요 처리 중 문제가 발생했습니다.");
        }

        return response;
    }
}
