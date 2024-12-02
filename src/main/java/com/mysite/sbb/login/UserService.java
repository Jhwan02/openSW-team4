package com.mysite.sbb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 회원가입 메서드
    public boolean register(String username, String id, String password) {
        logger.debug("회원가입 요청 - 아이디: {}, 사용자 이름: {}", id, username);
        if (userRepository.findById(id).isPresent()) {
            logger.debug("이미 존재하는 사용자 - 아이디: {}", id);
            return false; // 이미 존재하는 사용자
        }
        User user = new User();
        user.setUsername(username);
        user.setId(id);
        user.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        userRepository.save(user);
        logger.debug("회원가입 성공 - 아이디: {}", id);
        return true;
    }

    //로그인 메서드
    public User authenticate(String id, String password) {
        logger.debug("사용자 인증 요청 - 아이디: {}", id);
        User user = userRepository.findById(id).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            logger.debug("인증 성공 - 아이디: {}", id);
            return user; // 인증 성공
        }
        logger.debug("인증 실패 - 아이디: {}", id);
        return null; // 인증 실패
    }
    
    public User getUser(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }

    }


