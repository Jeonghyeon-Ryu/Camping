package com.camp.app.member.mapper;

import java.util.List;

import com.camp.app.member.service.MemberVO;

public interface MemberMapper {
	public List<MemberVO> findAll();
	public MemberVO findById(int memberId);
	public MemberVO findByEmail(int memberEmail);
//	persist
//	delete
//	existsById
//	count
	
}
