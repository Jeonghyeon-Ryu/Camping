package com.camp.app.camp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class InputCampVO {
	private int campId;
	private String campName;
	private String campAddress;
	private int campPrice;
	private int campSite;
	private List<String> campInfo;
	private List<MultipartFile> files;
}
