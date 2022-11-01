package com.camp.app.recru.web;

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

import com.camp.app.camp.service.CampVO;
import com.camp.app.recru.service.RecruService;
import com.camp.app.recru.service.RecruVO;
import com.camp.app.save.service.SaveVO;
/*
 * @author 최유리
 * 캠핑 동행 모집 게시판
 * 
 */
@CrossOrigin(origins = "*")
@RequestMapping("/java")
@RestController
public class RecruController {

	@Autowired
	RecruService service;
	
	//등록
	@PostMapping("/recru")
	public int recruInsert(@RequestBody RecruVO recruVO) {
		System.out.println(recruVO.getMemberId());
		return service.insertRecru(recruVO);
	}
	//전체조회
	@GetMapping("/recru")
	public List<RecruVO> recruAllList(){
		return service.recruAllList();
	}
	//전체조회- 페이징
	@GetMapping("/recru/page/{memberRole}/{pageNum}")
	public List<RecruVO> findPageList(@PathVariable int memberRole, @PathVariable int pageNum){
		RecruVO vo = new RecruVO();
		vo.setMemberRole(memberRole);
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
	//검색 조회 - 페이징
	@GetMapping("/recru/search/{memberRole}/{keyword}/{pageNum}")
	public List<RecruVO> recruSearchList(@PathVariable int memberRole, @PathVariable String keyword, @PathVariable int pageNum){
		RecruVO vo = new RecruVO();
		vo.setMemberRole(memberRole);
		vo.setKeyword(keyword);
		vo.setPageNum(pageNum);
		return service.recruKeywordList(vo);
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
		return service.changeShowStatus(recruVO);
	}
	//완료된 모집의 참가자들 조회(모집자+참가자)
	@GetMapping("/recru/members/{recruId}/{memberId}")
	public List<RecruVO> findRecruMembers(@PathVariable int recruId,@PathVariable String memberId){
		RecruVO vo = new RecruVO();
		vo.setRecruId(recruId);
		vo.setMemberId(memberId);
		return service.findRecruMembers(vo);
	}
	//후기등록
	@PostMapping("/recru/review")
	public int insertReview(@RequestBody RecruVO recruVO) {
		return service.insertReview(recruVO);
	}
	//캠핑장검색
	@PostMapping("/recru/campingPoint")
	public List<CampVO> searchCamp(@RequestBody CampVO campVO) {
		return service.searchCamp(campVO);
	}
}
