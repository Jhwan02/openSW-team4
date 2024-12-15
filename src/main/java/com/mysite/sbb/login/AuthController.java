package com.mysite.sbb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "main_page"; // 질문 목록으로 리다이렉트 로그인 창으로 수정 필요 
    }

    // 로그인 메서드
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request, HttpSession session) {
        logger.debug("(Auth)로그인 요청 - 아이디: {}", request.getId());
        User user = userService.authenticate(request.getId(), request.getPassword());
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            session.setAttribute("user", user); // 세션에 사용자 정보 저장
            logger.debug("(Auth)로그인 성공 - 아이디: {}", request.getId());
            response.put("success", true);
            response.put("username", user.getUsername());
            return ResponseEntity.ok(response);
        } else {
            logger.debug("(Auth)로그인 실패 - 아이디: {}", request.getId());
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // 회원가입 메서드
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        logger.debug("(Auth)회원가입 요청 - 아이디: {}", request.getId());
        boolean result = userService.register(request.getUsername(), request.getId(), request.getPassword());
        if (result) {
            logger.debug("(Auth)회원가입 성공 - 아이디: {}", request.getId());
            return ResponseEntity.ok("success");
        } else {
            logger.debug("(Auth)회원가입 실패 - 아이디: {}", request.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }
    @GetMapping("/session-info")
    public ResponseEntity<Map<String, Object>> getSessionInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("loggedIn", true);
            response.put("username", user.getUsername());
            response.put("id", user.getId());
            logger.debug("(Auth)세션 확인 - 로그인 상태: true, 아이디: {}, 이름: {}", user.getId(), user.getUsername());
            return ResponseEntity.ok(response);
        } else {
            response.put("loggedIn", false);
            logger.debug("(Auth)세션 확인 - 로그인 상태: false");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("로그아웃 성공");
    }

    // 회원탈퇴 메서드
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            logger.debug("(Auth)/user/delete 요청 - 아이디: {}", user.getId());
            userService.deleteUserById(user.getId());
            session.invalidate();
            logger.debug("(Auth)/user/delete 성공 - 아이디: {} 탈퇴완료", user.getId());
            return ResponseEntity.ok("회원탈퇴 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후 이용해주세요");
        }
    }

    //비밀번호 변경 메서드
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(String newPassword, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            logger.debug("(Auth)비밀번호 변경 요청 - 아이디: {}", user.getId());
            userService.changePassword(user.getId(), newPassword);
            logger.debug("(Auth)비밀번호 변경 성공 - 아이디: {}", user.getId());
            return ResponseEntity.ok("비밀번호 변경 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후 이용해주세요");
        }
    }
}
