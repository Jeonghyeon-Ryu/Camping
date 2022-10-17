package com.camp.app.sns.mapper;

import java.util.List;

import com.camp.app.sns.service.SnsCommentVO;

public interface SnsCommentMapper {

	// sns comment 댓글 달기
	public int insertSnsComment(SnsCommentVO snsComment);

	// comment 댓글 게시글별 전체 출력
	public List<SnsCommentVO> findByWriteNoToSnsComment(int writeNo);

	
}
