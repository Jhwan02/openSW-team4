package com.mysite.sbb.recruitmentQuestion;

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

import com.mysite.sbb.login.User;
import com.mysite.sbb.recruitmentAnswer.RecruitmentAnswerForm;
import com.mysite.sbb.upload.UploadController;
import com.mysite.sbb.upload.UploadResultDTO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/recruit")
@RequiredArgsConstructor
@Controller
public class RecruitmentQuestionController {

    private final RecruitmentQuestionService recruitQuestionService;
    private final UploadController uploadController; // 이미지 업로드 컨트롤러 추가

 // 질문 목록
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        // 페이징된 모집 질문 목록 가져오기
        Page<RecruitmentQuestion> paging = this.recruitQuestionService.getList(page);

        // 작성시간 포맷팅 처리
        List<Map<String, Object>> formattedQuestions = new ArrayList<>();
        for (RecruitmentQuestion question : paging.getContent()) {
            Map<String, Object> formattedQuestion = new HashMap<>();
            formattedQuestion.put("id", question.getId());
            formattedQuestion.put("subject", question.getSubject());
            formattedQuestion.put("category", question.getCategory() != null ? question.getCategory() : "카테고리 없음");
            formattedQuestion.put("author", question.getAuthor() != null ? question.getAuthor().getUsername() : "작성자 없음");

            // 작성 시간 포맷팅 처리
            String formattedDate;
            if (question.getCreateDate() != null) {
                formattedDate = recruitQuestionService.formatDateTime(question.getCreateDate());
            } else {
                formattedDate = "알 수 없음";
            }
            formattedQuestion.put("formattedDate", formattedDate);

            formattedQuestions.add(formattedQuestion);
        }

        // 모델에 데이터 추가
        model.addAttribute("paging", paging);
        model.addAttribute("formattedQuestions", formattedQuestions);

        return "recruit_list"; // recruit_list.html 렌더링
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


    //마이페이지 필요 api
    @GetMapping("/posts")
    @ResponseBody
    public Page<RecruitmentQuestion> listAsJson(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "author", required = false) String author) {
        
        // 작성자를 기준으로 검색
        if (author != null && !author.isEmpty()) {
            return this.recruitQuestionService.getListByAuthor(page, author);
        }

        // 작성자 필터가 없으면 모든 글 가져오기
        return this.recruitQuestionService.getList(page);
    }
}


