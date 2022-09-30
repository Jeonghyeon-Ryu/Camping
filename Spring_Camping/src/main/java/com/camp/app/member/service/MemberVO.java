package com.camp.app.member.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
	private int memberId;
	private String memberEmail;
	private String password;
	private String nickname;
	private String member_name;
	private String birth;
	private int sex;
	private int phoneNumber;
	private String profileInfo;
	private int memberStatus;
	private int memberAuth;
	private String socialEmail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;
}
