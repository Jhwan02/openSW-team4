package com.mysite.sbb.login;

public class LoginRequest {
    private String id;
    private String password;

    // 기본 생성자
    public LoginRequest() {}

    // 매개변수가 있는 생성자
    public LoginRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // Getter와 Setter 메서드
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
   
    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(String id) {
        this.id = id;
    }

}
