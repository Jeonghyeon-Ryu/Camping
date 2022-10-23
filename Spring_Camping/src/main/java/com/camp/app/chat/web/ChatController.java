package com.camp.app.chat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.camp.app.chat.service.ChatService;
import com.camp.app.chat.service.InputChatVO;

@Controller
@CrossOrigin(originPatterns = "*")
public class ChatController {
	
	@Autowired
	SimpMessageSendingOperations sendTemplate;
	@Autowired
	ChatService service;

	/** 
	 * @author 류정현
	 * @apiNote type( 0:생성 / 1:입장 / 2:퇴장 / 3:일반메세지 )
	 * @param 모든 서버요청 채팅 입력
	 * @return sendTemplate 으로 Websocket 메세지 리턴
	 * */
	@MessageMapping("/send")
	public void send(InputChatVO chat) {
		int type = chat.getType();
		System.out.println(chat);
		if(type==0) {				// 채팅방 생성
			int roomId = service.existRoom(chat);
			if(roomId>0) {			// 채팅방이 이미 존재할때 채팅방 입장으로 라우트
				chat.setRoomId(roomId);
				sendTemplate.convertAndSend("/queue/"+chat.getEmail(), chat);
				service.enterRoom(chat);
			} else {				// 채팅방이 없을때 채팅방 생성으로 라우트
				service.createRoom(chat);
			}
		} else if (type==1) {		// 채팅방 입장 ( 채팅방 구독 필요 )
			sendTemplate.convertAndSend("/queue/"+chat.getEmail(), chat);
			service.enterRoom(chat);
		} else if (type==2) {		// 채팅방 나가기
			
		} else if (type==3) {		// 일반 메세지 
			sendTemplate.convertAndSend("/queue/"+chat.getEmail(), chat);
			service.sendMessage(chat);
		} else {					// 그외 오류처리
			
		}
	}

}
