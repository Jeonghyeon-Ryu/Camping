package com.camp.app.chat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.chat.service.ChatService;
import com.camp.app.chat.service.InputChatVO;
import com.camp.app.chat.service.RoomVO;

@RestController
@CrossOrigin(originPatterns = "*")
public class RoomController {
	
	@Autowired
	ChatService service;

	@GetMapping("/roomlist/{email}")
	public List<RoomVO> getRoomAll(@PathVariable String email){
		return service.getRoomList(email);
	}
	
	@GetMapping("/room/{email}")
	public int isConnectQueue(@PathVariable String email) {
		return service.isConnectQueue(email);
	}
	
	@PostMapping("/room/{email}")
	public int connectQueue(@PathVariable String email) {
		return service.connectQueue(email);
	}
	
	@PostMapping("/room")
	public int existRoom(@RequestBody InputChatVO inputChat) {
		System.out.println("input Chat : existRoom : " + inputChat );
		return service.existRoom(inputChat);
	}
}
