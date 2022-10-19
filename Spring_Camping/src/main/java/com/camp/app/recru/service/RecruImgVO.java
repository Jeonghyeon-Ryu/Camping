package com.camp.app.recru.service;
/*
 * 캠핑 동행 모집글에 올린 이미지 정보
 * 담당 : 최유리
 * 221012
 */
import lombok.Data;

@Data
public class RecruImgVO {

	private int imageId;
	private String originName;
	private String storedName;
	private String imgFormat;
	private long imgSize;
	private String imgPath;
	private int recruId;
	
	private int isSaved;	//게시글의 저장여부
}
