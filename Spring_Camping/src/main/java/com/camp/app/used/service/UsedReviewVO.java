package com.camp.app.used.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsedReviewVO {
	private int reviewId; //후기ID
	private int usedId;
	private int email2;
	private int reviewGrade;
	private String reviewContent;
	private int reviewRole;
	private String email;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date reviewDate;
	
}
