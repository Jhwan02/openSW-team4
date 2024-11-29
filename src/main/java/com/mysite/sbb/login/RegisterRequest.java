package com.mysite.sbb.login;

public class RegisterRequest {
    private String username;    //유저 이름
    private String id;  //아이디
    private String password;    //패스워드

    // 기본 생성자
    public RegisterRequest() {}

    // 매개변수가 있는 생성자
    public RegisterRequest(String username, String id, String password) {
        this.username = username;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
