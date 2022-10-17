package com.camp.app.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.sns.mapper.SnsCommentMapper;
import com.camp.app.sns.service.SnsCommentService;
import com.camp.app.sns.service.SnsCommentVO;

@Service
public class SnsCommentServiceImpl implements SnsCommentService {

	@Autowired
	SnsCommentMapper mapper;

	// 댓글작성
	@Override
	public int addSnsComment(SnsCommentVO snsComment) {
//		System.out.println(snsComment);
//		int temp = mapper.insertSnsComment(snsComment);
//		System.out.println("출력댓글"+temp);
//		return temp;
		return mapper.insertSnsComment(snsComment);
		
	}

	//게시글별 댓글 전체출력
	@Override
	public List<SnsCommentVO> findByWriteNoToSnsComment(int writeNo) {
		return mapper.findByWriteNoToSnsComment(writeNo);
	}
	
	
}
