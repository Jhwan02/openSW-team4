package com.mysite.sbb.login;

public class PostResponse {
    private String boardName; // 게시판 이름
    private Object data;      // 실제 데이터 (RecruitmentQuestion 또는 Question)

    public PostResponse(String boardName, Object data) {
        this.boardName = boardName;
        this.data = data;
    }

    // Getters and Setters
    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
