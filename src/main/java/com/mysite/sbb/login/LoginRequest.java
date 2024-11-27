package com.mysite.sbb.login;

public class LoginRequest {
    private String username;
    private String password;

    // 기본 생성자
    public LoginRequest() {}

    // 매개변수가 있는 생성자
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter와 Setter 메서드
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
