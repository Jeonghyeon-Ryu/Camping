package com.camp.app.recru.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.recru.service.RecruService;
import com.camp.app.recru.service.RecruVO;

@CrossOrigin(origins = "*")
@RestController
public class RecruController {

	@Autowired
	RecruService service;
	
	//등록
	@PostMapping("/recru")
	public RecruVO recruInsert(@RequestBody RecruVO recruVO) {
		service.insertRecru(recruVO);
		return recruVO;
	}
	//전체조회
	@GetMapping("/recru")
	public List<RecruVO> recruAllList(){
		return service.recruAllList();
	}
	//검색 조회
	@PostMapping("/recru/{keyword}")
	public List<RecruVO> recruSearchList(@PathVariable String keyword){
		return service.recruKeywordList(keyword);
	}
	
	//단건조회
	@GetMapping("/recru/{recruId}")
	public RecruVO selectOne(@PathVariable String recruId) {
		return service.findOne(recruId);
	}
}
