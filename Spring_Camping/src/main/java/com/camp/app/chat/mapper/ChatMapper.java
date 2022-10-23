package com.camp.app.chat.mapper;

import java.util.List;

import com.camp.app.chat.service.ChatVO;

public interface ChatMapper {
	// 채팅 입력
	public int insert(ChatVO chat);
	// 방 채팅 내용 전체조회
	public List<ChatVO> findByRoomId(ChatVO chat);
	// 채팅 내용 단건 조회
	public ChatVO findByChatId(ChatVO chat);	
	// RoomId + ChatId(범위) 로 채팅 갯수 조회
	public int countByRoomIdAndChatId(ChatVO chat);
	// 채팅 입력위한 최대 채팅번호 조회
	public int findMaxChatId();
}
