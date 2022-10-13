package com.camp.app.camp.service;

import lombok.Data;

@Data
public class CampVO {
	private int campId;
	private String campName;
	private String campAddress;
	private int campPrice;
	private int campSite;
	private String campInfo;
}
