package com.camp.app.sns.service;

import lombok.Data;

@Data
public class SnsImageVO {
	//7개
	private int snsImageNo;
	private String originName;
	private String storedName;
	private String snsFormat;
	private long snsSize;
	private String snsPath;
	private int writeNo;
}
