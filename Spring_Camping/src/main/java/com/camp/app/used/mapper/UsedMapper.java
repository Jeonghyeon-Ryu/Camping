package com.camp.app.used.mapper;

import java.util.List;

import com.camp.app.save.service.SaveVO;
import com.camp.app.used.service.UsedImageVO;
import com.camp.app.used.service.UsedReviewVO;
import com.camp.app.used.service.UsedVO;

public interface UsedMapper {
	//게시글 번호 조회
	public int getUsedId();
	
	//게시글 등록
	public int insertUsed(UsedVO usedVO);
	
	public int insertUsedImage(UsedImageVO usedImage);
	
	public int findMaxByUsedId();
	public int findMaxByUsedImageId();
	public UsedVO findByUsedId(int usedId);
	public List<UsedImageVO> findByUsedIdToUsedImage(int usedId);
	
	
	//게시글 수정
	public void updateUsed(UsedVO usedVO);

	//게시글 전체조회
	public List<UsedVO> selectAllUsedList(int page);
	
	//게시글 단건조회
	public UsedVO findDetail(int usedId);
	
	//내가쓴글조회
	public List<UsedVO> findMyUsed(String email);
	
	//내가찜한글조회
	public List<SaveVO> findUsedSave(String email);
	
	//게시글 조건조회
	public List<UsedVO> searchUsedList(UsedVO used);
	
	//게시글 키워드조회
	public List<UsedVO> findUsedKeyword(String keyword);
	
	//거래상태변경
	public void updateDeal(UsedVO used);
	
	//게시물 제한(usedStatus=1)
	public void restrictUsed(UsedVO used);
	
	//게시물 삭제(usedStatus=2)
	public void deleteUsed(UsedVO used);
	
	//조회수증가
	public int updateCnt(int usedId);

	//찜 count
	public int updateLike(int usedId);
	
	//후기 작성
	public int insertReview(UsedReviewVO review);
	
}
