package com.camp.app.recru.mapper;

import java.util.List;

import com.camp.app.recru.service.RecruImgVO;

public interface RecruImgMapper {
	
	//이미지 등록
	public int insertRecru(RecruImgVO img);
	//게시물별 이미지
	public List<RecruImgVO> findRecruImg(int RecruId);
	//추가 등록
	public int addImg(RecruImgVO img);
	//이미지 삭제
	public int deleteImg(RecruImgVO img);
}
