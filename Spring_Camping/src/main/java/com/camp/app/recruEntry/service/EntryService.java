package com.camp.app.recruEntry.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EntryService {
	//동행신청 등록
	public int insertEntry(EntryVO entry);
	//모집글에 참가 신청을 넣은 회원 목록 조회
	public List<EntryVO> recruEntredList(int recruid);
	//마이페이지 참가글 목록
	public List<EntryVO> myEntryList(String memberId);
	//동행신청 상태수정 
	public int updateEntryStatus(EntryVO entry);
}
