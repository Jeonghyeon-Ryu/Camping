package com.camp.app.member.mapper;

import java.util.List;

import com.camp.app.member.service.MemberVO;
import com.camp.app.member.service.ProfileImageVO;

public interface MemberMapper {
	public List<MemberVO> findAll();
	public MemberVO findByEmail(String email);
	public MemberVO findByNickname(String nickname);
	public int count();
	public boolean existsByEmail(String email);
	public boolean existsByNickname(String nickname);
	public MemberVO findByEmailAndPassword(MemberVO member);
	public int insert(MemberVO member);
	public int update(MemberVO member);
	public int findMaxByProfileImageId();
	public int insertProfileImage(ProfileImageVO image);
	public ProfileImageVO findByEmailToProfileImage(String email);
	public MemberVO findBySocialEmail(String socialEmail);
//	persist
//	delete	
}
