package com.camp.app.recru;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.camp.app.recruEntry.service.EntryService;
import com.camp.app.recruEntry.service.EntryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class RecruTest {
	
	@Autowired
	EntryService service;
	
	@Test
	public void memberExistTest() {
        int recruId = 11;
        int entryCar = 0;
        String entryGear ="공구, 공구, 1";
        String memberId = "admin@test.com"; //작성자정보 -->세션에서 받아오기
        String nickname = "amdmin";
		
        EntryVO info = new EntryVO();
        info.setRecruId(recruId);
        info.setEntryCar(entryCar);
        info.setEntryGear(entryGear);
        info.setMemberId(memberId);
        info.setNickname(nickname);
        int result = service.insertEntry(info);
        
		assertEquals(1,result);
	}

}
