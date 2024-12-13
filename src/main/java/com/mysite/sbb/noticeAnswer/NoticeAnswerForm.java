package com.mysite.sbb.noticeAnswer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NoticeAnswerForm {
	@NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
