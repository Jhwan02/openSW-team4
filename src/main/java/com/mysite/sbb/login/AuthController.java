package com.mysite.sbb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // 로그인 메서드
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        User user = userService.authenticate(request.getUsername(), request.getPassword());
        if (user != null) {
            session.setAttribute("user", user); // 세션에 사용자 정보 저장
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
    }

    // 회원가입 메서드
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        boolean result = userService.register(request.getUsername(), request.getPassword());
        if (result) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }
}
