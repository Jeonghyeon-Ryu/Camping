package com.camp.app.member.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.member.service.MemberVO;

@RestController
@CrossOrigin
public class MemberController {
	
	
	@PostMapping("/login")
	public void login(@RequestBody MemberVO member) {
		System.out.println(member);
	}
}
