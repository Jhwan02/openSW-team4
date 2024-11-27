package com.mysite.sbb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 회원가입 메서드
    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false; // 이미 존재하는 사용자
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        userRepository.save(user);
        return true;
    }

    // 사용자 인증 메서드
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // 인증 성공
        }
        return null; // 인증 실패
    }
}
