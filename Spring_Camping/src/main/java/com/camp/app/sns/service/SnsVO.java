package com.camp.app.sns.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class SnsVO {
	//9개
	private int writeNo;
	private String content;
	private String location;
	private String place;
	private String hashtag;
	private Date writeDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private int status;
	private String implement;
	private String nickname;
}
