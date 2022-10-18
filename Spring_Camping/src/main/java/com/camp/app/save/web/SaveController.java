package com.camp.app.save.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.camp.app.save.service.SaveService;
import com.camp.app.save.service.SaveVO;

@Controller
@CrossOrigin
public class SaveController {
	
	@Autowired
	SaveService service;
	
	public List<SaveVO> showAll(){	// 모든 저장목록 불러오기
		
		return null;
	}
	
	@GetMapping("/save")
	public boolean isExist(SaveVO save) {	// 게시글 하나 들어갈때 저장된 목록인지 확인
		System.out.println("save : " + save);
		return true;
	}
	public boolean save(SaveVO save) {	// 저장하기
		
		return true;
	}
	public boolean remove(SaveVO save) {	// 저장목록에서 제거
		
		return true;
	}
}
