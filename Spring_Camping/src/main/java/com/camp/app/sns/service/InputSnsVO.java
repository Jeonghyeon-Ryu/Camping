package com.camp.app.sns.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class InputSnsVO {
	//10개
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
	private List<MultipartFile> files;
}
