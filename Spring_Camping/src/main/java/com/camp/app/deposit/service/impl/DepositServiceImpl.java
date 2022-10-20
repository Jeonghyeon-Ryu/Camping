package com.camp.app.deposit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.deposit.mapper.DepositMapper;
import com.camp.app.deposit.service.DepositService;
import com.camp.app.deposit.service.DepositVO;
@Service
public class DepositServiceImpl implements DepositService {
	
	@Autowired
	DepositMapper mapper;
	
	//등록
	@Override
	public int insertDeposit(DepositVO depositVO) {
		return mapper.insertDeposit(depositVO);
	}
	//상태수정
	@Override
	public int changeStatus(DepositVO depositVO) {
		return mapper.changeStatus(depositVO);
	}
	//마이페이지 단건조회
	@Override
	public DepositVO myDepositList(DepositVO depositVO) {
		return mapper.myDepositList(depositVO);
	}

}
