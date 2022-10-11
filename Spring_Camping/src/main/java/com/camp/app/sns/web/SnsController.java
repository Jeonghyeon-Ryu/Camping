package com.camp.app.sns.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camp.app.sns.service.SnsService;
import com.camp.app.sns.service.SnsVO;

//@SessionAttributes
@RestController
@CrossOrigin 
public class SnsController {
	
	@Autowired
	SnsService service;
	
	//검색 해시태그
	@GetMapping("/hashtag")
	public List<String> getHashtag(){
		return service.getHashTag();
	}
	
	//검색 닉네임
	@GetMapping("/snsnickname")
	public List<String> getNickname(){
		return service.getNickName();
	}
	
	//글쓰기
	@PostMapping("/sns")
	public boolean insert(@RequestBody SnsVO sns) {
		int result = service.insert(sns);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
//		System.out.println(sns);
		
	}
	
	
}
