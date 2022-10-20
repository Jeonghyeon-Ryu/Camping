package com.camp.app.save.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.save.service.SaveService;
import com.camp.app.save.service.SaveVO;

@RestController
@CrossOrigin(originPatterns = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
public class SaveController {
	
	@Autowired
	SaveService service;
	
	@GetMapping("/saves")
	public List<SaveVO> showAll(){	// 모든 저장목록 불러오기
		return service.showAll();
	}
	
	@GetMapping("/save")
	public boolean isExist(SaveVO save) {	// 게시글 하나 들어갈때 저장된 목록인지 확인
		return service.isExist(save);
	}
	
	@PostMapping("/save")
	public boolean save(@RequestBody SaveVO save) {	// 저장하기
		System.out.println("save : " + save);
		return service.save(save);
	}
	@DeleteMapping("/save")
	public boolean remove(@RequestBody SaveVO save) {	// 저장목록에서 제거		
		return service.remove(save);
	}
}
