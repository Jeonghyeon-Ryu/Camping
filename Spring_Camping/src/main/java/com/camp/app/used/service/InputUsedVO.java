package com.camp.app.used.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class InputUsedVO {
	private int usedId;
	private String usedWriter;
	private String nickName;
	private String usedName;
	private int usedPrice;
	private String usedCategory;
	private int usedCondition;
	private String usedContent;
	private List<String> usedPlace;
	private List<MultipartFile> files;
}
