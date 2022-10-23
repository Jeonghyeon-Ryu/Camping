package com.camp.app.chat.service;

import java.util.List;

import lombok.Data;

@Data
public class InputChatVO {
	private long roomId;
	private String email;
	private List<String> targetEmail;
	private String message;
	private int type;
	// 타입 : 0(채팅방 생성) / 1(채팅방 나가기) / 2(일반 메세지)
}
