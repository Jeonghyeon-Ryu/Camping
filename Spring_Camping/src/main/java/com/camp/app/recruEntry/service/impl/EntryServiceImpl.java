+package com.camp.app.recruEntry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.member.mapper.MemberMapper;
import com.camp.app.member.service.MemberVO;
import com.camp.app.recruEntry.mapper.EntryMapper;
import com.camp.app.recruEntry.service.EntryService;
import com.camp.app.recruEntry.service.EntryVO;
@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	EntryMapper mapper;
	@Autowired
	MemberMapper mMapper;
	
	//동행신청 등록
	@Override
	public int insertEntry(EntryVO entry) {
		return mapper.insertEntry(entry);
	}
	//모집글에 참가 신청을 넣은 회원 목록 조회
	@Override
	public List<EntryVO> recruEntredList(int recruId) {
		//모집글 아이디로 참자가 목록 가져오기
		List<EntryVO> list = mapper.recruEntredList(recruId);
		//각 참가자의 성별과 생년월일 정보에 추가
		for(EntryVO info : list) {
			String email = info.getMemberId();
			MemberVO member = mMapper.findByEmail(email);
			info.setEntryBirth(member.getBirth());
			info.setEntrySex(member.getSex());
		}
		return list;
	}
	//마이페이지 참가글 목록
	@Override
	public List<EntryVO> myEntryList(String memberId) {
		return mapper.myEntryList(memberId);
	}
	//모집완료된 참가글 목록(보증금관련)
	@Override
	public List<EntryVO> endEntryList(String memberId){
		return mapper.endEntryList(memberId);
	}
	//동행신청 상태수정 
	@Override
	public int updateEntryStatus(EntryVO entry) {
		return mapper.updateEntryStatus(entry);
	}

}
