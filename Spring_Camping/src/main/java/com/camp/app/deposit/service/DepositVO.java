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
	private int depositStatus;
	private int price;
	@JsonFormat(pattern = "yyyyMMdd")
	private Date inDate;
	private String returnMethod;
	@JsonFormat(pattern = "yyyyMMdd")
	private Date returnDate;
	private String memeberId;
}
