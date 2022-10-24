package com.camp.app.chat.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camp.app.chat.mapper.ChatMapper;
import com.camp.app.chat.mapper.ChatlogMapper;
import com.camp.app.chat.mapper.RoomMapper;
import com.camp.app.chat.service.ChatService;
import com.camp.app.chat.service.ChatVO;
import com.camp.app.chat.service.ChatlogVO;
import com.camp.app.chat.service.InputChatVO;
import com.camp.app.chat.service.RoomVO;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatMapper cMapper;
	@Autowired
	RoomMapper rMapper;
	@Autowired
	ChatlogMapper lMapper;
	@Autowired
	SimpMessageSendingOperations sendTemplate;
	
	@Transactional
	@Override
	public void createRoom(InputChatVO inputChat) {
		// Room 추가 + Chat 추가 + 방 정보를 돌려줌
		
		// Room 추가 ( 보낸사람 )
		RoomVO room = new RoomVO();
		room.setEmail(inputChat.getEmail());
		room.setRoomId(rMapper.findMaxRoomId());
//		rMapper.insert(room);
		ChatlogVO chatlog = new ChatlogVO();
		chatlog.setRoomId(room.getRoomId());
		chatlog.setEmail(room.getEmail());
		chatlog.setChatId(0);
//		lMapper.insert(chatlog);
		
		// Room 추가 ( 받는사람들 )
		System.out.println("Inputchat : " + inputChat);
		inputChat.getTargetEmail().forEach(email -> {
			System.out.println("InputChat Email : " + email);
			room.setEmail(email);
			rMapper.insert(room);
			chatlog.setEmail(email);
			lMapper.insert(chatlog);
		});
		
		// Chat 추가
		ChatVO chat = new ChatVO();
		chat.setChatId(cMapper.findMaxChatId());
		chat.setEmail(inputChat.getEmail());
		chat.setRoomId(room.getRoomId());
		chat.setMessage(inputChat.getEmail()+"님과의 채팅이 시작되었습니다.");
		cMapper.insert(chat);
		
		// 해당 방 유저들에게 모두 Queue 메세지 전송
//		sendTemplate.convertAndSend("/queue/"+chat.getEmail(), room);
		inputChat.getTargetEmail().forEach(email -> {
			sendTemplate.convertAndSend("/queue/"+email, room);
		});
	}

	@Transactional
	@Override
	public void exitRoom(InputChatVO inputChat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(InputChatVO inputChat) {
		ChatVO chat = new ChatVO();
		chat.setChatId(cMapper.findMaxChatId());
		chat.setEmail(inputChat.getEmail());
		chat.setRoomId(inputChat.getRoomId());
		chat.setMessage(inputChat.getEmail()+"님과의 채팅이 시작되었습니다.");
		cMapper.insert(chat);
		chat = cMapper.findByChatId(chat);
		
		ChatlogVO chatlog = new ChatlogVO();
		chatlog.setRoomId(inputChat.getRoomId());
		chatlog.setEmail(inputChat.getEmail());
		chatlog.setChatId(chat.getChatId());
		lMapper.update(chatlog);
		
		
		sendTemplate.convertAndSend("/topic/room/"+inputChat.getRoomId(), chat);
	}

	@Override
	public int isConnectQueue(String email) {
		RoomVO room = new RoomVO();
		room.setEmail(email);
		room.setRoomId(0);
		return rMapper.findByRoomIdAndEmail(room);
	}


	@Override
	public int connectQueue(String email) {
		RoomVO room = new RoomVO();
		room.setEmail(email);
		room.setRoomId(0);
		return rMapper.insert(room);
	}

	@Override
	public int existRoom(InputChatVO inputChat) {
		List<String> list = inputChat.getTargetEmail();
		list.add(inputChat.getEmail());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return rMapper.existRoom(map);
	}

	@Transactional
	@Override
	public void enterRoom(InputChatVO inputChat) {
		ChatVO chat = new ChatVO();
		chat.setRoomId(inputChat.getRoomId());
		// 채팅 리스트 다 받아와서 넘겨주기
		List<ChatVO> chatList = cMapper.findByRoomId(chat);
		// 읽은거까지 챗 로그 남기기
		ChatlogVO chatlog = new ChatlogVO();
		chatlog.setChatId(chatList.get(chatList.size()-1).getChatId());
		chatlog.setEmail(inputChat.getEmail());
		chatlog.setRoomId(inputChat.getRoomId());
		lMapper.update(chatlog);
		sendTemplate.convertAndSend("/queue/"+inputChat.getEmail(), chatList);
	}

	@Override
	public List<RoomVO> getRoomList(String email) {
		RoomVO room = new RoomVO();
		room.setEmail(email);
		return rMapper.findByEmail(room);
	}

}
