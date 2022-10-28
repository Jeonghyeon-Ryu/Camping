package com.camp.app.save.mapper;

import java.util.List;

import com.camp.app.save.service.SaveVO;
import com.camp.app.sns.service.MySnsLikeVO;
import com.camp.app.sns.service.SnsImageVO;

public interface SaveMapper {
	public List<SaveVO> findAll(); // 모든 저장목록 불러오기

	public List<SaveVO> findByEmailAndBoardDivision(SaveVO save);

	public int findMaxSaveId(); // SaveId 최대값 보기

	public boolean exist(SaveVO save); // 게시글 하나 들어갈때 저장된 목록인지 확인

	public boolean insert(SaveVO save); // 저장하기

	public boolean delete(SaveVO save); // 저장목록에서 제거

	// 유저가 좋아요한 총게시글 수
	public int countLikeSnsByUser(String email);

	// 게시글에 좋아요한 수 배포 후 추가
	public int countLikeSnsToWriteNo(int boardId);
	
	// 유저가 좋아요한 게시글 리스트 이미지보기
	public List<SnsImageVO> showLikeSnsByPageByUser(MySnsLikeVO mySnsLike);
}
