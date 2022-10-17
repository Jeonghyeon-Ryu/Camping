package com.camp.app.recru.mapper;

import java.util.List;

import com.camp.app.member.service.MemberVO;
import com.camp.app.recru.service.RecruVO;

public interface RecruMapper {
	//최신 글 번호 조회
	public int getMaxRecruId();
	//글 등록
	public int insertRecru(RecruVO recru);
	//전체조회
	public List<RecruVO> findAll();
	//검색 조회
	public List<RecruVO> findKeyword(String keyword);
	//단건 조회
	public RecruVO selectOne(String recruId);
	//마이페이지 조회
	public List<RecruVO> myRecru(String memberId);
	//상태변경
	public int changeStatus(RecruVO recru);
	//작성자 정보조회
	public RecruVO getMemberInfo(String email);
}
