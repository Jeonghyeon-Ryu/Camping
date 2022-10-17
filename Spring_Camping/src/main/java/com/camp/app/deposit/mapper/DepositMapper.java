package com.camp.app.deposit.mapper;

import java.util.List;

import com.camp.app.deposit.service.DepositVO;

/*
 * 캠핑 동행 확정시 모집자와 참가자 모두에게 보증금을 받고 여행 날짜 이후 반환해주는 시스템
 * 담당: 최유리
 */
public interface DepositMapper {
	//등록
	public int insertDeposit(DepositVO depositVO);
	//상태수정
	public int changeStatus(DepositVO depositVO);
	//최신번호
	public int getMaxDepositId();
	//마이페이지
	public List<DepositVO> myDepositList(String memberId);

}
