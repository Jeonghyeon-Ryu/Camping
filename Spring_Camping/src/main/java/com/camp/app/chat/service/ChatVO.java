package com.camp.app.chat.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatVO {
	private long chatId;
	private long roomId;
	private String email;
	private String message;
	@DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
	private Date regdate;
}

