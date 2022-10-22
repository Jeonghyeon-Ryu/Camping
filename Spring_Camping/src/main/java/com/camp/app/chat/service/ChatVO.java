package com.camp.app.chat.service;

import java.util.List;

import lombok.Data;

@Data
public class ChatVO {
	private String roomId;
	private String email;
	private List<String> targetEmail;
	private String content;
	private String regdate;
}

