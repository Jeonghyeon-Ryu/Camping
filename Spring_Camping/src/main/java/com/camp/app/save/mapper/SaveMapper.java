package com.camp.app.save.mapper;

import java.util.List;

import com.camp.app.save.service.SaveVO;

public interface SaveMapper {
	public List<SaveVO> findAll();	// 모든 저장목록 불러오기
	public int findMaxSaveId();		// SaveId 최대값 보기
	public boolean exist(SaveVO save);	// 게시글 하나 들어갈때 저장된 목록인지 확인
	public boolean insert(SaveVO save);	// 저장하기
	public boolean delete(SaveVO save);	// 저장목록에서 제거
}
