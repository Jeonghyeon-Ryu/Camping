package com.camp.app.recruEntry.mapper;

import java.util.List;

import com.camp.app.recruEntry.service.EntryVO;

public interface EntryMapper {

	//동행신청 등록
	public int insertEntry(EntryVO entry);
	//모집글의 참가신청 목록
	public List<EntryVO> recruEntredList(String recruId);
	//마이페이지 참가글 목록
	public List<EntryVO> myEntryList(String memberId);
	//동행신청 상태수정 
	public int updateEntryStatus(EntryVO entry);
	//
}
