package com.camp.app.sns.mapper;

import java.util.List;

import com.camp.app.sns.service.MySnsTagVO;
import com.camp.app.sns.service.SnsCommentVO;
import com.camp.app.sns.service.SnsImageVO;

public interface SnsCommentMapper {

	// sns comment 댓글 달기
	public int insertSnsComment(SnsCommentVO snsComment);

	// comment 댓글 게시글별 전체 출력
	public List<SnsCommentVO> findByWriteNoToSnsComment(int writeNo);

	// 유저가 태그당한 총게시글 수
	public int countTagSnsByUser(String nickname);

	// 유저가 태그당한 게시글 이미지리스트
	public List<SnsImageVO> showSnsTagByPageByUser(MySnsTagVO mySnsTag);
	
}
