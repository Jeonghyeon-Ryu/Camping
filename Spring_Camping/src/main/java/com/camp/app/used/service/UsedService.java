package com.camp.app.used.service;

import java.util.List;

public interface UsedService {
	//게시글 번호 조회
	public int getUsedId();
	
	//게시글 등록
	public boolean insertUsed(InputUsedVO usedVO);
	
	public int getMaxUsedId();
	public int getMaxUsedImageId();
	
	
	//게시글 수정
	public void updateUsed(UsedVO usedVO);
	
	//게시글 전체조회
	public List<UsedVO> selectAllUsedList();
	
	//게시글 단건조회
	public UsedVO findDetail(int usedId);
	
	//게시글 조건조회
	public List<UsedVO> searchUsedList();
	
	//게시글 키워드조회
	public List<UsedVO> findUsedKeyword(String keyword);
	
	//게시글 삭제
	public int deleteUsed();
	
	//거래상태변경
	public int updateDeal();
	
	//게시물상태변경
	public int updateRestrict();
	
	//조회수증가
	public int updateCnt(int usedId);

	//찜 증가
	public int updateLike();
}
