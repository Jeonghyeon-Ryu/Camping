package com.camp.app.sns.mapper;

import java.util.List;

import com.camp.app.sns.service.SnsVO;

public interface SnsMapper {

	// sns 전체조회
	public List<SnsVO> getSnsList();
	
	// sns 해시태그, 
	public List<String> getHashTag();
	
	//닉네임 검색 기능
	public List<String> getNickName();
	
	// sns 글쓰기 기능
	public int insert(SnsVO sns);
	
	// 글번호(writeNo 조회)
	public int writeNo();
	
	// sns 좋아요 기능

	// sns 글 (수정,) 삭제 기능

	// sns comment 댓글 달기

	// 신고...흠 

	//
}
