package com.camp.app.recruEntry.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.recruEntry.service.EntryService;
import com.camp.app.recruEntry.service.EntryVO;

@CrossOrigin(origins = "*")
@RequestMapping("/recru")
@RestController
public class EntryController {

	@Autowired
	EntryService service;
	
	//등록 
	@PostMapping("/entry")
	public int entryInsert(@RequestBody EntryVO entryVO) {
		return service.insertEntry(entryVO);
	}
	//수정
	
	//취소
	
	//마이페이지 전체 조회
	@PostMapping("/entry/{memberId}")
	public List<EntryVO> myEntryList(@PathVariable String memberId){
		return service.myEntryList(memberId);
	}
	
}
