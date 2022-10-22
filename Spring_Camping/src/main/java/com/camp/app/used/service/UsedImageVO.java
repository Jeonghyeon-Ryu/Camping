package com.camp.app.used.service;

import lombok.Data;

@Data
public class UsedImageVO {
	private int usedImageId;
	private int usedId;
	private String usedOriginName;
	private String usedStoredName;
	private String usedFormat;
	private long usedSize;
	private String usedPath;
}
