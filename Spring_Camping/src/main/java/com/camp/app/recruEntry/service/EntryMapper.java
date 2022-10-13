package com.camp.app.recruEntry.service;

public interface EntryMapper {

	//동행신청 등록
	public int insertEntry(EntryVO entry);
	//동행신청 상태수정 
	public int updateEntryStatus(EntryVO entry);
}
