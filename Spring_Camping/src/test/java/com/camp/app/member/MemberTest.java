package com.camp.app.member;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.camp.app.member.mapper.MemberMapper;
import com.camp.app.member.service.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class MemberTest {

	@Autowired
	MemberMapper mapper;
	
	@Test
	public void memberExistTest() {
		String email = "vxxv@naver.co";
		
		assertEquals(false,mapper.existsByEmail(email));
	}
	
//	@Test
//	public void memberInsertTest() {
//		MemberVO member = new MemberVO();
//		
//		member.setEmail("red@naver.com");
//		member.setPassword("aaa");
//		member.setNickname("으아");
//		member.setName("윤상혁");
//		member.setBirth("900102");
//		member.setSex("남");
//		member.setPhoneNumber("010-0000-0000");
//		assertEquals(1, mapper.insert(member));
//		
//	}
	
	@Test
	public void memberUpdateTest() {
		MemberVO member = new MemberVO();
		
		member.setEmail("red@naver.com");
		member.setProfileInfo("나의 프로필 입니다 ! 테스트용 프로필 내용 !");
		
		assertEquals(1, mapper.update(member));
	}
}
