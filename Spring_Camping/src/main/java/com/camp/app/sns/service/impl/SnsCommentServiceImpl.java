package com.camp.app.sns.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.sns.mapper.SnsCommentMapper;
import com.camp.app.sns.service.SnsCommentService;

@Service
public class SnsCommentServiceImpl implements SnsCommentService {

	@Autowired
	SnsCommentMapper mapper;
     
	

}
