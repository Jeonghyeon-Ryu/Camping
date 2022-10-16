package com.camp.app.member.mapper;

import java.util.List;
import java.util.Optional;

import com.camp.app.member.service.MemberVO;

public interface MemberMapper {
	public List<MemberVO> findAll();
	public MemberVO findByEmail(String email);
	public int count();
	public boolean existsByEmail(String email);
	public boolean existsByNickname(String nickname);
	public MemberVO findByEmailAndPassword(MemberVO member);
	public int insert(MemberVO member);
	public int update(MemberVO member);
//	persist
//	delete	
}
