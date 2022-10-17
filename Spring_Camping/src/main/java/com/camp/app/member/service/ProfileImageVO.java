package com.camp.app.member.service;

import lombok.Data;

@Data
public class ProfileImageVO {
	private int profileImageId;
	private String originName;
	private String storedName;
	private String imageFormat;
	private long imageSize;
	private String imagePath;
	private String email;
}
