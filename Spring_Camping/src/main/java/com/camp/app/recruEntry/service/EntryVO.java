package com.camp.app.recruEntry.service;
/*
 * 캠핑 동행 모집글에 대한 신청 VO
 * 담당 : 최유리
 * 221012
 */
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EntryVO {

	private int entryId;
	private int recruId;
	private int entryCar;
	private String entryGear;
	@JsonFormat(pattern = "yyyyMMdd")
	private Date entryDate;
	private int entryStatus;		//신청상태 (0신청중,1수락,2거절,3취소대기(모집완료후),4취소완료)
	private String memberId;
	private String nickname;		
	
	private int recruStatus;	//모집글 상태(0모집중,1모집완료,2실패,3여행완료)
	private String entrySex;	//신청자 정보
	private String entryBirth;
	
	private String recruiter;	//모집자 id
	private String title;
	private String content;
	@JsonFormat(pattern = "yyyyMMdd")
	private Date goDate;
	@JsonFormat(pattern = "yyyyMMdd")
	private Date comeDate;
	
	private int pageNum;
}
