package com.camp.app.recruEntry.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.recruEntry.service.EntryService;
import com.camp.app.recruEntry.service.EntryVO;
/*
 * @author : 최유리
 * 캠핑 모집글에 참가 신청이 들어온 내역 관리
 */
@CrossOrigin(origins = "*")
@RequestMapping("/java/recru")
@RestController
public class EntryController {

	@Autowired
	EntryService service;
	
	//등록 
	@PostMapping("/entry")
	public int entryInsert(@RequestBody EntryVO entryVO) {
		return service.insertEntry(entryVO);
	}
	//모집글에 참가 신청을 넣은 회원 목록 조회
	@GetMapping("/entry/{recruId}")
	public List<EntryVO> recruEnteredList(@PathVariable String recruId) {
		int recruid = Integer.parseInt(recruId);
		return service.recruEnteredList(recruid);
	}
	//마이페이지 전체 조회
	@GetMapping("/entry/mypage/{memberId}/{pageNum}")
	public List<EntryVO> myEntryList(@PathVariable String memberId, @PathVariable int pageNum){
		//String memberId = (String)session.getAttribute("email");
		EntryVO info = new EntryVO();
		info.setMemberId(memberId);
		info.setPageNum(pageNum);
		return service.myEntryList(info);
	}
	//모집완료된 참가글 목록(보증금관련)
	@PostMapping("/entry/success/{memberId}")
	public List<EntryVO> endEntryList(@PathVariable String memberId){
		return service.endEntryList(memberId);
	}
	//동행신청 상태수정 
	@PutMapping("/entry")
	public int updateEntryStatus(@RequestBody EntryVO entry) {
		return service.updateEntryStatus(entry);
	}
	
}
