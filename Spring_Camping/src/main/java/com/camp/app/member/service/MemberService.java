package com.camp.app.member.service;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
	public MemberVO login(MemberVO member);
}
