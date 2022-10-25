package com.camp.app.used.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.camp.app.used.service.UsedReviewVO;
import com.camp.app.used.service.UsedService;
import com.camp.app.used.service.UsedVO;
/**
 * 
 * @author 조하영
 * 중고거래 게시판
 *
 */
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
		service.updateUsed(used);
	}
	
	//거래상태수정
	@PutMapping("/dealUpdate")
	public void updateDealStatus(@RequestBody UsedVO used) {
		service.updateDeal(used);
	}
	
	//게시물 상태 수정 (제한)
	@PutMapping("/statusRestrict")
	public void restrictStatus(@RequestBody UsedVO used) {
		service.restrictUsed(used);
	}
	
	//게시물 상태 수정 (삭제)
	@PutMapping("/statusUpdate")
	public void updateStatus(@RequestBody UsedVO used) {
		service.deleteUsed(used);
	}
	
	//좋아요
	@GetMapping("/updateLike/{usedId}")
	public int usedLike(@PathVariable int usedId){
		return service.updateLike(usedId);
	}
	
	//전체조회
	@GetMapping("/usedMain/{page}")
	public List<UsedVO> selectAllUsedList(@PathVariable int page) {
		return service.selectAllUsedList(page);
	}
	
	//전체조회
//	@GetMapping("/usedMain")
//	public List<UsedVO> selectAllUsedList() {
//		return service.selectAllUsedList();
//	}
	
//	//내가쓴글조회 
//	@PostMapping("/myUsed")
//	public List<UsedVO> findMyUsed(HttpSession session){
//		String usedWriter = (String)session.getAttribute("email");
//		return service.findMyUsed(usedWriter);
//	}
	
	//내가쓴글조회
	@PostMapping("/myUsed/{usedWriter}")
	public List<UsedVO> findMyUsed(@PathVariable String usedWriter){
		return service.findMyUsed(usedWriter);
	}
	
	//키워드검색 조회
	@PostMapping("/search/{keyword}")
	public List<UsedVO> findUsedKeyword(@PathVariable String keyword){
		return service.findUsedKeyword(keyword);
	}
	
	//필터검색  ?
	@PostMapping("/usedSearch")
	public List<UsedVO> searchUsedList(@RequestBody UsedVO used){
		return service.searchUsedList(used);
	}
	
	//단건조회
	@GetMapping("/usedDetail/{usedId}")
	public UsedVO findDetail(@PathVariable int usedId) {
		service.updateCnt(usedId);
		return service.findDetail(usedId);
	}
		
	//사진
	@GetMapping("/usedImage/{usedId}")
	public List<UsedImageVO> getCampImageList(@PathVariable("usedId") int usedId){
		System.out.println(usedId);
		return service.showUsedImageByUsedId(usedId);
	}
	
	//사진보여주기
	@GetMapping("/showImage/{usedPath}/{usedStoredName}")
	public ResponseEntity<Resource> showImage(@PathVariable String usedPath, @PathVariable String usedStoredName){
		String fullPath = "d:\\upload\\used\\" + usedPath + "\\" + usedStoredName;
		System.out.println("*** FullPath : " +fullPath);
		Resource resource = new FileSystemResource(fullPath);
		
		if(!resource.exists()) {
			System.out.println("File Not Found ! ");
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders header = new HttpHeaders();
		Path filePath = null;
		
		try {
			filePath = Paths.get(fullPath);
			System.out.println(filePath);
			header.add("Content-Type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	//리뷰등록
	@PostMapping("/usedReview")
	public int usedReview(UsedReviewVO review) {
		return service.insertReview(review);
	}
	

}
