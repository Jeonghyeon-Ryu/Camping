package com.camp.app.security;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.camp.app.member.service.MemberVO;

public class MyAuthentication extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 1L;

	MemberVO member;

	public MyAuthentication(String id, String pw, List authList, MemberVO member) {
		super(id, pw, authList);
		this.member = member;
	}
}
