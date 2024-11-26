package com.mysite.sbb.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpSession session) {
        User user = userService.authenticate(request.getUsername(), request.getPassword());
        if (user != null) {
            // 로그인 성공 시 세션에 유저 정보 저장
            session.setAttribute("user", user);
            return "success";
        } else {
            return "fail";
        }
    }
}

// LoginRequest DTO 클래스
class LoginRequest {
    private String username;
    private String password;

    // Getter, Setter
}
