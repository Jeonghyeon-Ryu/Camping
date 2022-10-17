package com.camp.app.sns.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SnsCommentService {

	// sns comment 댓글 달기
	public int addSnsComment(SnsCommentVO snsComment);
	
}
