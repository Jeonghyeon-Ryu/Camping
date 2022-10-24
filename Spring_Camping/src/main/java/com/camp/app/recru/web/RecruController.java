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
/*
 * @author 최유리
 * 캠핑 동행 모집 게시판
 * 
 */
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
	//전체조회- 페이징
	@GetMapping("/recru/page/{pageNum}")
	public List<RecruVO> findPageList(@PathVariable int pageNum){
		RecruVO vo = new RecruVO();
		vo.setPageNum(pageNum);
		return service.findAllPaging(vo);
	}
	//단건조회
	@GetMapping("/recru/{recruId}")
	public RecruVO selectOne(@PathVariable int recruId) {
		return service.findOne(recruId);
	}
	//마이페이지 조회 -수정
	@GetMapping("/recru/mypage/{memberId}")
	public List<RecruVO> myRecru(@PathVariable String memberId){
		return service.myRecru(memberId);
	}
	//검색 조회
	@GetMapping("/recru/search/{keyword}")
	public List<RecruVO> recruSearchList(@PathVariable String keyword){
		return service.recruKeywordList(keyword);
	}
	//모집상태변경
	@PutMapping("/recru")
	public int changeStatus(@RequestBody RecruVO recruVO) {
		return service.changeStatus(recruVO);
	}
	//게시물의 저장여부 -수정
	@GetMapping("/recru/save/{email}/{boardDivision}/{boardId}")
	public boolean isExist(@PathVariable String email,@PathVariable int boardDivision, @PathVariable int boardId) {
		SaveVO save = new SaveVO();
		save.setEmail(email);
		save.setBoardDivision(boardDivision);
		save.setBoardId(boardId);
		return service.isExist(save);
	}
	//내용수정
	@PutMapping("/recru/updateAll")
	public int updateRecru(@RequestBody RecruVO recruVO) {
		return service.updateRecru(recruVO);
	}
	//접근 상태 변경
	@PutMapping("/recru/showStatus")
	public int changeShowStatus(@RequestBody RecruVO recruVO) {
		System.out.println(recruVO.getStatus());
		return service.changeShowStatus(recruVO);
	}
	//후기등록
	@PostMapping("/recru/review")
	public int insertReview(@RequestBody RecruVO recruVO) {
		return service.insertReview(recruVO);
	}
}
