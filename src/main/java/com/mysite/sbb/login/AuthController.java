package com.mysite.sbb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    // 로그인 메서드
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        logger.debug("로그인 요청 - 아이디: {}", request.getId());
        User user = userService.authenticate(request.getId(), request.getPassword());
        if (user != null) {
            session.setAttribute("user", user); // 세션에 사용자 정보 저장
            logger.debug("로그인 성공 - 아이디: {}", request.getId());
            return ResponseEntity.ok("success");
        } else {
            logger.debug("로그인 실패 - 아이디: {}", request.getId());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
    }

    // 회원가입 메서드
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        logger.debug("회원가입 요청 - 아이디: {}", request.getId());
        boolean result = userService.register(request.getUsername(), request.getId(), request.getPassword());
        if (result) {
            logger.debug("회원가입 성공 - 아이디: {}", request.getId());
            return ResponseEntity.ok("success");
        } else {
            logger.debug("회원가입 실패 - 아이디: {}", request.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }
}
