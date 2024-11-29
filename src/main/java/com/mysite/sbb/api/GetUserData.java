package com.mysite.sbb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mysite.sbb.login.User;
import com.mysite.sbb.login.UserRepository;

@Service
public class GetUserData {

    @Autowired
    private UserRepository userRepository;

    // 모든 사용자 정보를 가져오는 메서드
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 특정 사용자 정보를 ID로 가져오는 메서드
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // 모든 사용자의 아이디와 이름을 가져오는 메서드
    public List<UserDTO> getAllUserIdsAndNames() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }
}