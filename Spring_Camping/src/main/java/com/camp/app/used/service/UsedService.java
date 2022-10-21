package com.camp.app.used.service;

import java.util.List;

import com.camp.app.save.service.SaveVO;

public interface UsedService {
	//게시글 번호 조회
	public int getUsedId();
	
	//게시글 등록
	public boolean insertUsed(InputUsedVO usedVO);
	
	public int getMaxUsedId();
	public int getMaxUsedImageId();
	
	//게시글이미지
	public List<UsedImageVO> showUsedImageByUsedId(int usedId);
	
	//게시글 수정
	public void updateUsed(UsedVO usedVO);
	
	//게시글 전체조회
	public List<UsedVO> selectAllUsedList();
	
	//게시글 단건조회
	public UsedVO findDetail(int usedId);
	
	//내가쓴글조회
	public List<UsedVO> findMyUsed(String usedWriter);
	
	//게시글 조건조회
	public List<UsedVO> searchUsedList();
	
	//게시글 키워드조회
	public List<UsedVO> findUsedKeyword(String keyword);
	
	//거래상태
	public void updateDeal(UsedVO used);
	
	//게시글 제한(usedStatus=1)
	public void restrictUsed(UsedVO used);
	
	//게시글 삭제(usedStatus=2)
	public void deleteUsed(UsedVO used);
	
	//조회수증가
	public int updateCnt(int usedId);

	//찜 증가
	public int updateLike(int usedId);

	//??
	int updateRestrict();
	
	//후기 작성
	public int insertReview(UsedReviewVO review);
	
}
