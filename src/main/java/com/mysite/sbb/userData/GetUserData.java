package com.mysite.sbb.userData;
import com.mysite.sbb.login.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class GetUserData {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @GetMapping("/getuserdata")
    public ResponseEntity<Map<String, Object>> getSessionInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("username", user.getUsername());
            response.put("id", user.getId());
            logger.debug("접속 유저 정보 아이디: {}, 이름: {}", user.getId(), user.getUsername());
            return ResponseEntity.ok(response);
        } else {
            logger.debug("로그인 상태: false");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
