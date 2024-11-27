package com.mysite.sbb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 회원가입 메서드
    public boolean register(String name, String username, String password) {
        logger.debug("회원가입 요청 - 이름: {}, 아이디: {}", name, username);
        if (userRepository.findByUsername(username) != null) {
            logger.debug("이미 존재하는 사용자 - 아이디: {}", username);
            return false; // 이미 존재하는 사용자
        }
        User user = new User();
        user.setId(name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        userRepository.save(user);
        logger.debug("회원가입 성공 - 아이디: {}", username);
        return true;
    }

    // 사용자 인증 메서드
    public User authenticate(String username, String password) {
        logger.debug("사용자 인증 요청 - 아이디: {}", username);
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            logger.debug("인증 성공 - 아이디: {}", username);
            return user; // 인증 성공
        }
        logger.debug("인증 실패 - 아이디: {}", username);
        return null; // 인증 실패
    }
}
