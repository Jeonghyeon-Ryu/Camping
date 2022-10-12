package com.camp.app.recru.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface RecruImgService {
	
	//등록
	public boolean insertRecruImg(List<MultipartFile> files);
	//게시물별 사진
	public List<RecruImgVO> findImg(int recruId);
	//수정
	
}
