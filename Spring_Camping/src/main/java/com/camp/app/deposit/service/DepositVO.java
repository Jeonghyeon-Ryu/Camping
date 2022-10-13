package com.camp.app.deposit.service;
/*
 * 캠핑 동행 참가시 보내는 보증금을 관리
 * 담당 : 최유리
 * 221012
 */
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DepositVO {

	private int depositId;				
	private int recruId;	
	private int depositStatus;//보증금 상태(0미입금, 1입금완료, 2반환완료)
	private int price;
	@JsonFormat(pattern = "yyyyMMdd")
	private Date inDate;	//보증금 입금일
	private String returnMethod;	//반환방식
	@JsonFormat(pattern = "yyyyMMdd")
	private Date returnDate;	//반환일자
	private String memeberId;	
	
}
