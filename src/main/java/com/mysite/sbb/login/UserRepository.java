package com.mysite.sbb.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username); // 메서드 선언	
	Optional<User> findById(String id); // 메서드 선언
	void deleteById(String id); // 특정 유저 삭제
}
