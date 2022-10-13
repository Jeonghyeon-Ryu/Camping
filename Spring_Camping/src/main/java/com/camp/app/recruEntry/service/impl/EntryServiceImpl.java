package com.camp.app.recruEntry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.recruEntry.mapper.EntryMapper;
import com.camp.app.recruEntry.service.EntryService;
import com.camp.app.recruEntry.service.EntryVO;
@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	EntryMapper mapper;
	
	//동행신청 등록
	@Override
	public int insertEntry(EntryVO entry) {
		return mapper.insertEntry(entry);
	}
	//모집글에 참가 신청을 넣은 회원 목록 조회
	@Override
	public List<EntryVO> recruEntredList(String recruId) {
		return mapper.recruEntredList(recruId);
	}
	//마이페이지 참가글 목록
	@Override
	public List<EntryVO> myEntryList(String memberId) {
		return mapper.myEntryList(memberId);
	}

}
