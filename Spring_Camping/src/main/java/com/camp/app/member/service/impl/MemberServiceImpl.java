package com.camp.app.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.member.mapper.MemberMapper;
import com.camp.app.member.service.MemberService;
import com.camp.app.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper mapper;
	
	@Override
	public MemberVO login(MemberVO member) {
		MemberVO result = mapper.findByEmail(member.getMemberEmail());
		if(result==null) {}
		return result;
	}

}
