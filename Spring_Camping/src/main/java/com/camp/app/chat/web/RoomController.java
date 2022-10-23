package com.camp.app.chat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.chat.service.ChatService;
import com.camp.app.chat.service.InputChatVO;

@RestController
@CrossOrigin(originPatterns = "*")
public class RoomController {
	
	@Autowired
	ChatService service;
	
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
