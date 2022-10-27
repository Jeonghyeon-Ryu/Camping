package com.camp.app.recru.service;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/* 
 * 캠핑 동행 모집게시글 정보
 * */
import lombok.Data;

@Data
public class RecruVO {
	private int recruId;			//모집글 ID
	private String memberId;			//작성자 이메일
	private String nickname;			//작성자 닉네임
	
	private String birth;				//작성자 생일 (200101)
	private String sex;					//작성자 성별
	
	private String recruTitle;		//모집글 제목
	private String recruContent;	//모집글 내용
	private Date writeDate;			//글 작성일
	private	String wishAge;			//동행 희망 나이대(20대~50대이상)
	private int wishSex; 		//동행 희망 성별(0무관, 1남, 2여)
	private int carYn;				//차량유무 (0 없음, 1있음)
	private String myGear;			//보유 장비
	private String needGear;		//필요 장비
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date goDate;			//캠핑 출발날짜
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date comeDate;			//귀가날짜
	private String startingPoint;	//출발지
	private String campingPoint;	//도착지(캠핑장)
	private int noteId;				//삽입할 노트 ID
	private int recruNum;			//모집 인원
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date closingDate;		//모집 마감일
	private int recruStatus;		//모집상태(0 모집중, 1 모집완료, 2 실패, 3 여행완료)
	private int status;				//게시글상태(0 공개, 1 유저삭제, 2 관리자삭제)
	
	private int memberRole;		//(0 일반상태,1 작성자,2 참가자,3 관리자)
	
	private int depositId;		//게시글 모집 완료시 보증정보 인서트
	
	private String keyword;
	private int pageNum;
	
	private int reviewId;
	private String reviewTarget;
	private int reviewGrade;
	private String reviewContent;
}
