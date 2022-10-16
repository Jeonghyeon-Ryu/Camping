package com.camp.app.recru.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.member.mapper.MemberMapper;
import com.camp.app.member.service.MemberService;
import com.camp.app.member.service.MemberVO;
import com.camp.app.recru.mapper.RecruMapper;
import com.camp.app.recru.service.RecruService;
import com.camp.app.recru.service.RecruVO;
import com.camp.app.recruEntry.mapper.EntryMapper;

@Service
public class RecruServiceImpl implements RecruService {

	@Autowired
	RecruMapper mapper;
	@Autowired
	EntryMapper entryMapper;
	@Autowired
	MemberMapper memberMapper;
	
	//게시글 입력
	@Override
	public int insertRecru(RecruVO recruVO) {
		return mapper.insertRecru(recruVO);
	}
	//전체 조회
	@Override
	public List<RecruVO> recruAllList() {
		return mapper.findAll();
	}
	//검색 조회
	@Override
	public List<RecruVO> recruKeywordList(String keyword) {
		return mapper.findKeyword(keyword);
	}
	//최신아이디 조회
	@Override
	public int getMaxRecruId() {
		return mapper.getMaxRecruId();
	}
	//단건조회
	@Override
	public RecruVO findOne(String recruId) {
		//아이디로 게시글 가져오기
		RecruVO info = mapper.selectOne(recruId);
		//게시글 작성자 정보 가져오기
		String email = info.getMemberId();
		MemberVO member = memberMapper.findByEmail(email);
		
		info.setBirth(member.getBirth());
		info.setSex(member.getSex());
		info.setNickname(member.getNickname());
		return info;
	}
	//상태변경
	@Override
	public int changeStatus(RecruVO recruVO) {
		return mapper.changeStatus(recruVO);
	}
	//작성자 정보 (생일, 성별)
	@Override
	public RecruVO getMemberInfo(String email) {
		return mapper.getMemberInfo(email);
	}
	//마이페이지 
	@Override
	public List<RecruVO> myRecru(String memberId) {
		return mapper.myRecru(memberId);
	}

}
