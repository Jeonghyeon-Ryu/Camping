package com.camp.app.deposit.service;

import org.springframework.stereotype.Service;

@Service
public interface DepositService {
	//등록
	public int insertDeposit(DepositVO depositVO);
	//상태수정
	public int changeStatus(DepositVO depositVO);
}
