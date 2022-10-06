package com.camp.app.member.mapper;

import java.util.List;

import com.camp.app.member.service.MemberVO;

public interface MemberMapper {
	public List<MemberVO> findAll();
	public MemberVO findById(int memberId);
	public MemberVO findByEmail(String memberEmail);
	// save
	public int insert(MemberVO member);
	public int update(MemberVO member);
//	delete
	public boolean existsById(int memberId);
	public int count();
	
}
