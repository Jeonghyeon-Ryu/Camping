package com.camp.app.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChatService {
	// 채팅방 생성
	public void createRoom(InputChatVO inputChat);
	// 채팅방 나가기
	public void exitRoom(InputChatVO inputChat);
	// 일반 메세지
	public void sendMessage(InputChatVO inputChat);
	// 채팅방 전체 조회
	
	// Queue 연결여부 조회
	public int isConnectQueue(String email);
	// Queue 연결 저장
	public int connectQueue(String email);
	
	// 채팅방 존재 여부 확인
	public int existRoom(InputChatVO inputChat);
	// 채팅방 입장
	public void enterRoom(InputChatVO inputChat);
}
