package com.mysite.sbb.Board.Recruit.recruitmentQuestion;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitmentQuestionForm {
	    @NotEmpty(message="제목은 필수항목입니다.")
	    @Size(max=200)
	    private String subject;

	    @NotEmpty(message="내용은 필수항목입니다.")
	    private String content;
	    
	    /*카테고리메세지*/
	    @NotBlank(message = "카테고리선택은 필수항목입니다.")
	    private String category;
}
