package com.camp.app.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.member.mapper.MemberMapper;
import com.camp.app.member.service.MemberService;
import com.camp.app.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberMapper mapper;
	
	// show add modify remove ...
	// 회원 목록 보기 ( 관리자 )
	public List<MemberVO> showAllMember() {
		return mapper.findAll();
	}
	
	// 회원 제한 ( 관리자 )
	public int restrictMember(MemberVO member) {
		member.setStatus(1);
		return mapper.update(member);
	}
	
	// 회원 가입
	@Override
	public int signup(MemberVO member) {
		return mapper.insert(member);
	}
	
	// 회원 가입 이메일 확인
	@Override
	public boolean checkEmail(String email) {
		return mapper.existsByEmail(email);
	}
	
	// 회원 가입 닉네임 확인
	@Override
	public boolean checkNickname(String nickname) {
		return mapper.existsByNickname(nickname);
	}
	
	// 회원 정보 수정
	@Override
	public int modifyMember(MemberVO member) {
		return mapper.update(member);
	}
	
	// 회원 비밀번호 변경
	@Override
	public int modifyPassword(MemberVO member) {
		return mapper.update(member);
	}
	
	// 회원 탈퇴
	@Override
	public int signout(MemberVO member) {
		member.setStatus(0);
		return mapper.update(member);
	}

	@Override
	public MemberVO login(MemberVO member) {
		return mapper.findByEmailAndPassword(member);
	}
}
