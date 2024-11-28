package com.mysite.sbb.login;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_TABLE")
public class User {

    @Id
    private String id;
    private String username;
    private String password;

    // Getter 메서드
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setter 메서드
    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
