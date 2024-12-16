package com.mysite.sbb.Board.Notice.noticeAnswer;

import java.time.LocalDateTime;

import com.mysite.sbb.Board.Notice.notice.Notice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NoticeAnswer {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(columnDefinition = "TEXT")
	    private String content;

	    private LocalDateTime createDate;

	    @ManyToOne
	    private Notice question; 
}
