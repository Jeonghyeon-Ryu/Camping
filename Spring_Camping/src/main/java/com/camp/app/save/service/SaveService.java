package com.camp.app.save.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SaveService {
	public List<SaveVO> showAll();	// 모든 저장목록 불러오기
	public boolean isExist(SaveVO save);	// 게시글 하나 들어갈때 저장된 목록인지 확인
	public boolean save(SaveVO save);	// 저장하기
	public boolean remove(SaveVO save);	// 저장목록에서 제거
}
