package com.camp.app.recru.mapper;

import java.util.List;

import com.camp.app.recru.service.RecruImgVO;

public interface RecruImgMapper {
	
	//이미지 등록
	public int insertRecru(RecruImgVO img);
	//게시물별 이미지
	public List<RecruImgVO> findRecruImg(int RecruId);
}
