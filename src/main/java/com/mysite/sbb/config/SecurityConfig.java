package com.mysite.sbb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/img/**","/**","/h2-console/**","/auth/login","/auth/register").permitAll() // 정적 리소스 허용   
                .anyRequest().authenticated() // 다른 모든 요청은 인증 필요
            )
            .formLogin(form -> form
                .loginPage("/login") // 커스텀 로그인 페이지 설정 (선택 사항)
                .failureUrl("/") // 로그인 실패 시 리다이렉트할 URL
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/logout") // 로그아웃 후 리다이렉트 경로
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // 쿠키 삭제
            )
            .csrf(csrf -> csrf
                //.ignoringRequestMatchers("/h2-console/**") // H2 Console의 CSRF 비활성화
                .disable() // CSRF 비활성화 (로그인 문제 임시 해결)
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin()) // H2 Console의 iframe 사용 허용
            );
        
        return http.build();
    }
}