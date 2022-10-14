package com.camp.app.sns.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.camp.app.camp.service.CampImageVO;
import com.camp.app.camp.service.CampVO;

@Service
public interface SnsService {

	// sns 전체조회
	public List<SnsVO> getSnsList();

	//
	// sns 해시태그, 닉네임 검색
	public List<String> getHashTag();

	// 닉네임 검색 기능
	public List<String> getNickName();

	// 글쓰기
	public int insertSns(SnsVO vo);

	

	// 글 갯수 세기(페이징할때)
	public int count();

	// 글번호 max
	public int findMaxByWriteNo();

	// 이미지번호 max
	public int findMaxBySnsImageNo();

	
	// 정현님의..  이미지+텍스트인듯
	// 전체출력
	public List<SnsVO> showSnsAll();
	
	// 페이징
	public List<SnsImageVO> showSnsByPage(int page);
	
	// sns 개별조회 - 텍스트
	public SnsVO showSnsOne(int writeNo);
	
	// sns 개별조회 - 이미지 리스트
	public List<SnsImageVO> showSnsImageByWriteNo(int writeNo);
	
	// 글 + 이미지 등록
	public boolean addSns(InputSnsVO sns);
	
//	
//	// sns 단건 게시글 조회
//	public SnsVO getSns(int writeNo);

	// sns 글 (수정)
	public void snsUpdate(SnsVO vo);

	// sns 삭제 기능
	public void snsDelete(int writeNo);

}
