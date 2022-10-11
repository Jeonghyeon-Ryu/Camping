package com.camp.app.camp.service;

import lombok.Data;

@Data
public class CampImageVO {
	private int campImageId;
	private String originName;
	private String storedName;
	private String format;
	private long size;
	private String path;
	private int campId;
}
