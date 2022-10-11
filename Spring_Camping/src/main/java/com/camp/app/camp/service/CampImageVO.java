package com.camp.app.camp.service;

import lombok.Data;

@Data
public class CampImageVO {
	private int campImageId;
	private String originName;
	private String storedName;
	private String format;
	private int size;
	private String path;
	private int campId;
}
