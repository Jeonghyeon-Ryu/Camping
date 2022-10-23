package com.camp.app.chat.mapper;

import com.camp.app.chat.service.ChatlogVO;

public interface ChatlogMapper {
	// 최초 방 생성 후 입력
	public int insert(ChatlogVO chatlog);
	// 채팅을 읽었을때 ChatId 변경
	public int update(ChatlogVO chatlog);
	// 방 나갔을때 삭제
	public int delete(ChatlogVO chatlog);
	// 유저가 해당방에 어디까지 읽었는지 조회
	public ChatlogVO findByEmailAndRoomId(ChatlogVO chatlog);
}
