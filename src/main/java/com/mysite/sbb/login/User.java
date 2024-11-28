package com.mysite.sbb.login;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_TABLE")
public class User {

    private String username;
    @Id //이 어노테이션으로 id가 id로 지정됨
    private String id;
    private String password;

    // Getter 메서드
    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    // Setter 메서드
    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
