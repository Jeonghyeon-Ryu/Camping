package com.camp.app.chat.mapper;

import java.util.HashMap;
import java.util.List;

import com.camp.app.chat.service.RoomVO;

public interface RoomMapper {
	public int insert(RoomVO room);
	public int delete(RoomVO room);
	// 내가 참여하고 있는 방 탐색
	public List<RoomVO> findByEmail(RoomVO room);
	// 방에 참여하고 있는 유저들 검색
	public int findByRoomId(RoomVO room);
	// 룸번호 + 이메일 검색 ( Queue 확인용 )
	public int findByRoomIdAndEmail(RoomVO room);
	// 룸Id 할당 위해 조회
	public int findMaxRoomId();
	// 채팅방 존재 여부 확인
	public int existRoom(HashMap<String, Object> map);
}
