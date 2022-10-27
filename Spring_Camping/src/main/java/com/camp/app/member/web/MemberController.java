package com.camp.app.member.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.member.service.AuthVO;
import com.camp.app.member.service.MemberService;
import com.camp.app.member.service.MemberVO;
import com.camp.app.member.service.ProfileImageVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/java")
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
	
	@GetMapping("/member/{email}")
	public MemberVO user(@PathVariable String email) {
		return service.findByEmail(email);
	}
	
	@GetMapping("/emailByNickname/{nickname}")
	public MemberVO emailByNickname(@PathVariable("nickname") String nickname) {
		System.out.println(nickname);
		return service.findByNickname(nickname);
	}
	
	@GetMapping("/member/{email}/{password}")
	public boolean verifyMember(@PathVariable String email, @PathVariable String password) {
		MemberVO member = new MemberVO();
		member.setEmail(email);
		member.setPassword(password);
		System.out.println(member);
		if(password.equals(service.findByEmail(email).getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	@PostMapping("/member")
	public boolean signup(@RequestBody MemberVO member) {
		System.out.println(member);
		int result = service.signup(member);
		System.out.println("Result : " + result);
		if(result>0) {
			return true;
		} else {
			return false;
		}
	}
	@PutMapping("/member")
	public boolean modifyPassword(@RequestBody MemberVO member) {
		if(service.modifyPassword(member)>0) {
			return true;
		}else {
			return false;
		}
	}
	@GetMapping("/profile/{email}")
	public ProfileImageVO profileImage(@PathVariable String email){
		return service.showProfileImage(email);
	}
	@GetMapping("/profile/{imagePath}/{storedName}")
	public ResponseEntity<Resource> showImage(@PathVariable String imagePath, @PathVariable String storedName){
		return service.showImage(imagePath, storedName);
	}
	
	@PostMapping("/member/modify")
	public boolean modifyMember(MemberVO member, MultipartFile file) {
		int result = service.modifyMember(member, file);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@PostMapping("/login")
	public MemberVO login(@RequestBody MemberVO member, HttpServletRequest request) {
		MemberVO result = service.login(member);
		System.out.println("email " + result.getEmail());
		if(result != null) {
			request.getSession(true).setAttribute("email", result.getEmail());
			System.out.println(request.getSession().getAttribute("email"));
		}
		return result;
	}
	
	@PostMapping("/logout")
	public boolean logout(SessionStatus status) {
		if(!status.isComplete()) {
			status.setComplete();
		}
		return true;
	}
	
	
}
