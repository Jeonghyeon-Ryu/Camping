package com.camp.app.recru.mapper;

import java.util.List;
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
	//상태변경
	public int changeStatus(RecruVO recru);
}
