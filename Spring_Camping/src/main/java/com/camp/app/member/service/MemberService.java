package com.camp.app.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
	// show add modify remove
	// 회원 목록 보기 ( 관리자 )
	public List<MemberVO> showAllMember();
	// 회원 제한 ( 관리자 )
	public int restrictMember(MemberVO member);
	// 회원 가입 ( 일반 )
	public int signup(MemberVO member);
	// 회원 가입 이메일 확인 
	public boolean checkEmail(String email);
	// 회원 가입 닉네임 확인
	public boolean checkNickname(String nickname);
	// 회원 정보수정 ( 일반 )
	public int modifyMember(MemberVO member);
	// 회원 비밀번호 찾기 ( 일반 )
	public int modifyPassword(MemberVO member);
	// 회원 탈퇴 ( 일반 )
	public int signout(MemberVO member);
	// 로그인
	public MemberVO login(MemberVO member);
}
