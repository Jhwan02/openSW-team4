package com.mysite.sbb.recruitmentQuestion;

import com.mysite.sbb.login.User;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionForm;
import com.mysite.sbb.recruitmentAnswer.RecruitmentAnswerForm;
import com.mysite.sbb.upload.UploadController;
import com.mysite.sbb.upload.UploadResultDTO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/recruit")
@RequiredArgsConstructor
@Controller
public class RecruitmentQuestionController {

    private final RecruitmentQuestionService recruitQuestionService;
    private final UploadController uploadController; // 이미지 업로드 컨트롤러 추가

    // 질문 목록
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<RecruitmentQuestion> paging = this.recruitQuestionService.getList(page);
        model.addAttribute("paging", paging);
        return "recruit_list";
    }

    // 질문 상세 보기
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, RecruitmentAnswerForm recruitAnswerForm) {
        RecruitmentQuestion question = recruitQuestionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("recruitAnswerForm", new RecruitmentAnswerForm());
        return "recruit_detail";
    }

    @GetMapping("/create")
    public String questionCreate(HttpSession session,RecruitmentQuestionForm RecruitmentquestionForm) {
        // 세션에서 사용자 정보 확인
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/auth/login";
        }
        // 로그인된 상태라면 질문 작성 폼으로 이동
        return "recruit_form";
    }

  
    @PostMapping("/create")
    public String questionCreate(
            @Valid RecruitmentQuestionForm RecruitmentquestionForm,
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
            return "recruit_form"; // 유효성 검사 실패 시 폼 재표시
        }

        RecruitmentQuestion question;
        if (file != null && !file.isEmpty()) {
            try {
                UploadResultDTO uploadResult = uploadController.uploadFile(file);
                question = this.recruitQuestionService.create(
                        RecruitmentquestionForm.getSubject(),
                        RecruitmentquestionForm.getContent(),
                        RecruitmentquestionForm.getCategory(),
                        uploadResult.getImageURL(),
                        user
                );
            } catch (Exception e) {
                model.addAttribute("uploadError", "이미지 업로드 중 문제가 발생했습니다.");
                return "recruit_form"; // 에러 발생 시 폼 재표시
            }
        } else {
            question = this.recruitQuestionService.create(RecruitmentquestionForm.getSubject(), 
            											  RecruitmentquestionForm.getContent(), 
            											  RecruitmentquestionForm.getCategory(),user);
        }

        this.recruitQuestionService.save(question);

        return "redirect:/recruit/list"; // 질문 목록으로 리다이렉트
    }

   

    // 제목으로 질문 검색 API
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
