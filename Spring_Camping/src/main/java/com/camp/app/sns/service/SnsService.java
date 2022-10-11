package com.camp.app.sns.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SnsService {

	// sns 전체조회
	public List<SnsVO> getSnsList();

	// sns 해시태그,
	public List<String> getHashTag();

	// 닉네임 검색 기능
	public List<String> getNickName();
	
	//글쓰기
	public int insert(SnsVO vo);
}
