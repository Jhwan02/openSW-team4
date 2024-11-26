package com.mysite.sbb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        // DB에서 유저 정보 확인
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
