package com.camp.app.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.sns.mapper.SnsMapper;
import com.camp.app.sns.service.SnsService;
import com.camp.app.sns.service.SnsVO;

@Service
public class SnsServiceImpl implements SnsService {

	@Autowired
	SnsMapper mapper;
     
	@Override
	public List<SnsVO> getSnsList() {
		return mapper.getSnsList();
	}

	@Override
	public List<String> getHashTag() {
		List<String> hashTag = mapper.getHashTag();
		return hashTag;
	}

	@Override
	public List<String> getNickName() {
		List<String> nickName = mapper.getNickName();
		return nickName;
	}
	
	@Override
	public int insert(SnsVO vo) {
		return mapper.insert(vo);
	}




}
