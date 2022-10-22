package com.camp.app.recru;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.camp.app.recru.service.RecruService;
import com.camp.app.recru.service.RecruVO;
import com.camp.app.recruEntry.service.EntryService;
import com.camp.app.recruEntry.service.EntryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class RecruTest {
	
	@Autowired
	EntryService service;
	RecruService rservice;
	
	@Test
	public void memberExistTest() {
        int recruId = 11;
//        int entryCar = 0;
//        String entryGear ="공구, 공구, 1";
//        String memberId = "admin@test.com"; //작성자정보 -->세션에서 받아오기
//        String nickname = "amdmin";
//		
//        EntryVO info = new EntryVO();
//        info.setRecruId(recruId);
//        info.setEntryCar(entryCar);
//        info.setEntryGear(entryGear);
//        info.setMemberId(memberId);
//        info.setNickname(nickname);
//        int result = service.insertEntry(info);
//        
//		assertEquals(1,result);
		
//        List<EntryVO> list = service.recruEntredList(recruId);
//        for(EntryVO info : list ) {
//        	System.out.println(info.getMemberId());
//        }
        
		//아이디로 게시글 가져오기
		RecruVO info =rservice.findOne("11");
		//게시글 작성자 정보 가져오기
		//String email = info.getMemberId();
//		RecruVO member = rservice.getMemberInfo(email);
//		
//		info.setBirth(member.getBirth());
//		info.setSex(member.getSex());
        System.out.println(info.getMemberId());
	}

}
