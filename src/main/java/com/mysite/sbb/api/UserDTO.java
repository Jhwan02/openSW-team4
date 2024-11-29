package com.mysite.sbb.api;

public class UserDTO {
    private String id;
    private String username;

    public UserDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getter 메서드
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    // Setter 메서드
    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}