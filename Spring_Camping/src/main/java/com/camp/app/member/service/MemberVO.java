package com.camp.app.member.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
	// 12개
	private String email;
	private String socialEmail;
	private String password;
	private String nickname;
	private String name;
	private String birth;
	private String sex;
	private String phoneNumber;
	private String profileInfo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated;
	private int status;
	private int auth;
}
