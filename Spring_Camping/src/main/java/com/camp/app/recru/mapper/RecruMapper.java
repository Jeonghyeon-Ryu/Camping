package com.camp.app.recru.mapper;

import java.util.List;

import com.camp.app.recru.service.RecruImgVO;
import com.camp.app.recru.service.RecruVO;

public interface RecruMapper {
	//최신 글 번호 조회
	public int getMaxRecruId();
	//글 등록
	public int insertRecru(RecruVO recru);
	//전체조회
	public List<RecruVO> findAll();
	//전체조회 - 페이징
	public List<RecruVO> findAllPaging(RecruVO recruVO);
	//검색 조회 - 페이징
	public List<RecruVO> findKeyword(RecruVO recruVO);
	//단건 조회
	public RecruVO selectOne(int recruId);
	//마이페이지 조회
	public List<RecruVO> myRecru(String memberId);
	//상태변경
	public int changeStatus(RecruVO recru);
	//작성자 정보조회
	public RecruVO getMemberInfo(String email);
	//모집글 이미지 찾기
	public List<RecruImgVO> findImgByRecruId(int recruId);
	//글 수정
	public int updateRecru(RecruVO recruVO);
	//접근 상태 변경
	public int changeShowStatus(RecruVO recruVO);
	//완료된 모집의 참가자들 조회(모집자+참가자)
	public List<RecruVO> findRecruMembers(int recruId);
	//후기등록
	public int insertReview(RecruVO recruVO);
}
