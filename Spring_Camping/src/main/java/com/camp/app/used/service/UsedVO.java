package com.camp.app.used.service;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsedVO {
	private int usedId; //게시글id
	private String nickName; //작성자닉네임
	private String usedWriter; //작성회원번호
	private String usedName; //상품명
	private String usedCategory; //카테고리
	private int usedPrice; //상품가격
	private String usedPlace; //거래지역
	private String usedContent; //상품설명
	private int dealStatus; //거래상태(거래가능:0,거래중:1)
	private int usedStatus; //게시글상태(열람가능:0,비공개/삭제:1)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date usedWrite; //작성일
	private String buyerId; //구매자회원번호
	private int usedCondition; //상품상태(상 0 중 1 하 2)
	private int usedCnt; //조회수
	private int usedLike; //찜횟수
	private int minPrice; //최소가격
	private int maxPrice; //최대가격
	private String keyword;
}
