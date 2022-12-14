package com.camp.app.sns.mapper;

import java.util.List;

import com.camp.app.sns.service.MySnsTagVO;
import com.camp.app.sns.service.MySnsVO;
import com.camp.app.sns.service.SnsHashtagVO;
import com.camp.app.sns.service.SnsImageVO;
import com.camp.app.sns.service.SnsVO;

public interface SnsMapper {

	// sns 전체조회
	public List<SnsVO> getSnsList();

	// 페이징
	public List<SnsImageVO> findByPage(int page);

	// sns 해시태그,
	public List<String> getHashTag();

	// 닉네임 검색 기능
	public List<String> getNickName();

	// sns 글쓰기 기능
	public int insertSns(SnsVO sns);

	// sns 이미지 쓰기
	public int insertSnsImage(SnsImageVO snsImage);

	// 글 갯수 세기(페이징할때)
	public int count();

	// 글번호 max
	public int findMaxByWriteNo();

	// 이미지번호 max
	public int findMaxBySnsImageNo();

	// sns 개별조회 - 텍스트
	public SnsVO findByWriteNo(int writeNo);

	// sns 개별조회 - 이미지 리스트
	public List<SnsImageVO> findByWriteNoToSnsImage(int writeNo);

	// sns 글 (수정,)
	public int snsUpdate(SnsVO vo);

	// sns 삭제 기능
	public void snsDelete(int writeNo);

	// sns admin 삭제 기능
	public void snsDeleteByAdmin(int writeNo);

	// sns 좋아요 기능

	// 유저가 작성한 총게시글 수
	public int countSnsByUser(String email);

	
	//내가 쓴 게시글 이미지보기
	public List<SnsImageVO> showSnsByPageByUser(MySnsVO mySns);
	// 신고...흠

	//해시태그검색한 게시글 이미지리스트 출력
	public List<SnsImageVO> showSnsByPageByHashtag(SnsHashtagVO snsHashtag);



	


	//
}
