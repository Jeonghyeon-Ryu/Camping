package com.camp.app.camp.service;

import lombok.Data;

@Data
public class CampImageVO {
	private int campImageId;
	private String originName;
	private String storedName;
	private String imageFormat;
	private long imageSize;
	private String imagePath;
	private int campId;
}
