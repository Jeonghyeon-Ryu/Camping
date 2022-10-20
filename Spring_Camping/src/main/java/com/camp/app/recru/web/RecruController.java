package com.camp.app.recru.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.recru.service.RecruService;
import com.camp.app.recru.service.RecruVO;
import com.camp.app.save.service.SaveVO;

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
	//단건조회
	@GetMapping("/recru/{recruId}")
	public RecruVO selectOne(@PathVariable String recruId) {
		return service.findOne(recruId);
	}
	//마이페이지 조회
	@GetMapping("/recru/mypage/{memberId}")
	public List<RecruVO> myRecru(@PathVariable String memberId){
		return service.myRecru(memberId);
	}
	//검색 조회
	@GetMapping("/recru/search/{keyword}")
	public List<RecruVO> recruSearchList(@PathVariable String keyword){
		return service.recruKeywordList(keyword);
	}
	//상태변경
	@PutMapping("/recru")
	public int changeStatus(@RequestBody RecruVO recruVO) {
		return service.changeStatus(recruVO);
	}
	//게시물의 저장여부
	@GetMapping("/recru/save/{email}/{boardDivision}/{boardId}")
	public boolean isExist(@PathVariable String email,@PathVariable int boardDivision, @PathVariable int boardId) {
		SaveVO save = new SaveVO();
		save.setEmail(email);
		save.setBoardDivision(boardDivision);
		save.setBoardId(boardId);
		return service.isExist(save);
	}
	//수정
	@PutMapping("/recru/updateAll")
	public int updateRecru(@RequestBody RecruVO recruVO) {
		return service.updateRecru(recruVO);
	}
}
