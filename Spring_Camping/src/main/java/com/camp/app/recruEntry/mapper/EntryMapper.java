package com.camp.app.recruEntry.mapper;

import java.util.List;

import com.camp.app.recruEntry.service.EntryVO;

public interface EntryMapper {

	//동행신청 등록
	public int insertEntry(EntryVO entry);
	//모집글의 참가신청 목록
	public List<EntryVO> recruEnteredList(int recruId);
	//마이페이지 참가글 목록
	public List<EntryVO> myEntryList(EntryVO entry);
	//모집완료된 참가글 목록(보증금관련)
	public List<EntryVO> endEntryList(String memberId);
	//동행신청 상태수정 
	public int updateEntryStatus(EntryVO entry);
	//참가수락자 수
	public int recruEnteredNum(EntryVO entry);
}
