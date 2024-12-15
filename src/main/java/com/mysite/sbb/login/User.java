package com.mysite.sbb.login;

import java.util.List;

import com.mysite.sbb.recruitmentQuestion.RecruitmentQuestion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class User {

    private String username;
    @Id
    @Column(name = "id")
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

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RecruitmentQuestion> questions;
}