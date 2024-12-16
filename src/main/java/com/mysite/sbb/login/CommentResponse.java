package com.mysite.sbb.login;

import com.mysite.sbb.Board.Free.answer.Answer;
import com.mysite.sbb.Board.Recruit.recruitmentAnswer.RecruitmentAnswer;

public class CommentResponse {
    private String boardName; // 게시판 이름
    private Object data;      // 실제 데이터 (RecruitmentQuestion 또는 Question)
    private String questionTitle;
    private String questionId;
    
    public CommentResponse(String boardName, Object data) {
        this.boardName = boardName;
        this.data = data;
        this.questionTitle = getQuestionTitle();
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

    public String getQuestionTitle() {
        if (data instanceof RecruitmentAnswer) {
            RecruitmentAnswer recruitmentAnswer = (RecruitmentAnswer) data;
            return recruitmentAnswer.getQuestion() != null ? recruitmentAnswer.getQuestion().getSubject() : "No Title";
        } else if (data instanceof Answer) {
            Answer answer = (Answer) data;
            return answer.getQuestion() != null ? answer.getQuestion().getSubject() : "No Title";
        }
        return "Unknown";
    }

    public String getQuestionId() {
        if (data instanceof RecruitmentAnswer) {
            RecruitmentAnswer recruitmentAnswer = (RecruitmentAnswer) data;
            return recruitmentAnswer.getQuestion() != null ? recruitmentAnswer.getQuestion().getId().toString() : "No Id";
        } else if (data instanceof Answer) {
            Answer answer = (Answer) data;
            return answer.getQuestion() != null ? answer.getQuestion().getId().toString() : "No Id";
        }
        return "Unknown";
    }
}
