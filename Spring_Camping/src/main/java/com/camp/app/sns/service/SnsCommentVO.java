package com.camp.app.sns.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SnsCommentVO {
	//5개
	private int commentId;
	private String commentContent;
	private Date commentDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private int writeId;
	private String email;
}
