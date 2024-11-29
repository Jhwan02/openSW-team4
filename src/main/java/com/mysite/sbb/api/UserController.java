package com.mysite.sbb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // 로그인된 사용자의 아이디와 이름을 가져오는 엔드포인트
    @GetMapping("/current")
    public UserDTO getCurrentUser(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            return new UserDTO(user.getId(), user.getUsername());
        } else {
            throw new RuntimeException("사용자가 로그인되어 있지 않습니다.");
        }
    }
}