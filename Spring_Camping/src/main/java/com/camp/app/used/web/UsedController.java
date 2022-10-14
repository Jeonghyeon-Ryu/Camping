package com.camp.app.used.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.used.service.InputUsedVO;
import com.camp.app.used.service.UsedImageVO;
import com.camp.app.used.service.UsedService;
import com.camp.app.used.service.UsedVO;

@CrossOrigin(originPatterns = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
//@CrossOrigin(origins="*")
@RestController
@RequestMapping("/used")
public class UsedController {
	
	
	@Autowired
	UsedService service;
	
	//등록
	@PostMapping("/usedInsert")
	public boolean insertUsed(InputUsedVO used) {
		System.out.println(used);
		return service.insertUsed(used);
	}
	
	//수정
	@PutMapping("/usedUpdate")
	public void updateUsed(@RequestBody UsedVO used) {
//		System.out.println(used);
		service.updateUsed(used);
	}
	
	//전체조회
	@GetMapping("/usedMain")
	public List<UsedVO> selectAllUsedList() {
		return service.selectAllUsedList();
	}
	
	//키워드검색 조회
	@PostMapping("/search/{keyword}")
	public List<UsedVO> findUsedKeyword(@PathVariable String keyword){
		return service.findUsedKeyword(keyword);
	}
	
	//필터검색  ?
	@GetMapping("/usedSearch")
	public List<UsedVO> searchUsedList(){
		return service.searchUsedList();
	}
	
	//단건조회
	@GetMapping("/usedDetail/{usedId}")
	public UsedVO findDetail(@PathVariable int usedId) {
		service.updateCnt(usedId);
		return service.findDetail(usedId);
	}
	
}
