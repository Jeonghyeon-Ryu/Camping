package com.camp.app.recru.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.camp.app.save.service.SaveVO;

@Service
public interface RecruService {
	//최신글번호 조회
	public int getMaxRecruId();
	//등록
	public int insertRecru(RecruVO recruVO);
	//전체조회
	public List<RecruVO> recruAllList();
	//검색조회
	public List<RecruVO> recruKeywordList(String keyword);
	//단건조회
	public RecruVO findOne(String recruId);
	//마이페이지 조회
	public List<RecruVO> myRecru(String memberId);
	//상태변경
	public int changeStatus(RecruVO recru);
	//작성자 정보조회
	public RecruVO getMemberInfo(String email);
	//글 수정
	public int updateRecru(RecruVO recruVO);
	//저장정보(찜) 확인
	public boolean isExist(SaveVO save);
	
}
