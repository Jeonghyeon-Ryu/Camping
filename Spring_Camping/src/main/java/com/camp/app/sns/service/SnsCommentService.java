package com.camp.app.sns.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SnsCommentService {

	// sns comment 댓글 달기
	public int addSnsComment(SnsCommentVO snsComment);
	
	// 게시글별 댓글 전체 출력
	public List<SnsCommentVO> findByWriteNoToSnsComment(int writeNo);
}
