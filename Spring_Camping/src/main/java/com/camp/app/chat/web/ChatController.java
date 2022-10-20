package com.camp.app.chat.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.camp.app.chat.service.SocketVO;

@Controller
@CrossOrigin(originPatterns = "*")
public class ChatController {
	@MessageMapping("/receive")
	@SendTo("/send")
	public SocketVO SocketHandler(SocketVO socketVO) {
		String userName = socketVO.getUserName();
		String content = socketVO.getContent();
		
		SocketVO result = new SocketVO(userName, content);
		
		return result;
	}
}
