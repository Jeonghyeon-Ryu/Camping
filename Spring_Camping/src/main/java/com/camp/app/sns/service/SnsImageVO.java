package com.camp.app.sns.service;

import lombok.Data;

@Data
public class SnsImageVO {
	//7개
	private  int snsImageId;
	private String originName;
	private String storedName;
	private String format;
	private int size;
	private String path;
	private int writeId;
}
