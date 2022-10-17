package com.camp.app.member.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.camp.app.member.service.AuthVO;
import com.camp.app.member.service.MemberService;
import com.camp.app.member.service.MemberVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;

@RestController
@CrossOrigin(originPatterns = "*")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/email")
	public boolean isEmail(@RequestParam String email) {
		return !service.checkEmail(email);
	}
	
	@GetMapping("/nickname")
	public boolean isNickname(@RequestParam String nickname) {
		return !service.checkNickname(nickname);
	}
	
	@GetMapping("/auth")
	public AuthVO isAuth(@RequestParam String impUid) throws IOException, IamportResponseException {
		IamportClient client = new IamportClient("4717174862060476", "ycuRhOuX16mxrzq72ZYGxvEhg1PmAsLKyYPnviIlq0nTy7ePYB9cZRaJik9Pff1axfPjvw5h4Idn3xCI");
		IamportResponse<Certification> certificationResponse = client.certificationByImpUid(impUid);
		
		AuthVO auth = new AuthVO();
		auth.setName(certificationResponse.getResponse().getName());
		auth.setPhoneNumber(certificationResponse.getResponse().getPhone());
		auth.setBirth(certificationResponse.getResponse().getBirth());
		
		return auth;
	}
	@GetMapping("/member")
	public List<MemberVO> users() {
		return service.showAllMember();
	}
	@PostMapping("/member")
	public boolean signup(@RequestParam MemberVO member) {
		int result = service.signup(member);
		if(result>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@PostMapping("/login")
	public MemberVO login(@RequestBody MemberVO member) {
		return service.login(member);
	}
	
	@PostMapping("/logout")
	public boolean logout(SessionStatus status) {
		if(!status.isComplete()) {
			status.setComplete();
		}
		return true;
	}
	
}
