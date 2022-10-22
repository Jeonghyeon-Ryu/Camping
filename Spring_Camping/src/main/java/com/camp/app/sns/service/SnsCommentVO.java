package com.camp.app.sns.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SnsCommentVO {
	//5개
	private int commentNo;
	private String commentContent;
	private Date commentDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private int writeNo;
	private String nickname;
	private String email;
}
