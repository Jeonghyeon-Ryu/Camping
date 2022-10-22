package com.camp.app.sns;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.camp.app.sns.service.SnsService;
import com.camp.app.sns.service.SnsVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class SnsTest {

	@Autowired
	SnsService service;
//	SnsMapper mapper;
	
//	@Test
//	public void snsAllSelectTest() {
//		
//		SnsVO sns = new SnsVO();
//		//전체조회
//		List<SnsVO> list = service.getSnsList();
//		
//	}
	//글쓰기
	@Test
	public void snsInsertTest() {
		SnsVO sns = new SnsVO();

		sns.setWriteNo(2);
		sns.setContent("만두동 맛있다.. 다음엔 온모밀 먹어야지..");
		sns.setLocation("대구시 수성구 만촌동");
		sns.setPlace("이마트");
		sns.setHashtag("#만두 #치킨 # 피자 #집에갈래");
		sns.setNickname("user11@naver.com");
		sns.setImplement("취사도구");
	
		
	
	}
}
