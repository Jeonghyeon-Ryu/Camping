package com.camp.app.deposit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.deposit.service.DepositService;
import com.camp.app.deposit.service.DepositVO;

@CrossOrigin
@RequestMapping("/recru")
@RestController
public class DepositController {

	@Autowired
	DepositService service;
	
	//등록
	@PostMapping("/deposit")
	public int insertDeposit(@RequestBody DepositVO depositVO) {
		return service.insertDeposit(depositVO);
	}
	//상태수정
	@PutMapping("/deposit")
	public int changeStatus(@RequestBody DepositVO depositVO) {
		return service.changeStatus(depositVO);
	}
	//마이페이지 단건조회
	@GetMapping("/deposit/{memberId}/{recruId}")
	public DepositVO myDepositList(@PathVariable String memberId,@PathVariable int recruId) {
		DepositVO vo = new DepositVO();
		vo.setMemberId(memberId);
		vo.setRecruId(recruId);
		return service.myDepositList(vo);
	}
}
