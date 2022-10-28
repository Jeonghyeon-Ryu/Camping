package com.camp.app.sns.web;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.sns.service.InputSnsVO;
import com.camp.app.sns.service.MySnsLikeVO;
import com.camp.app.sns.service.SnsCommentService;
import com.camp.app.sns.service.SnsCommentVO;
import com.camp.app.sns.service.SnsImageVO;
import com.camp.app.sns.service.SnsService;
import com.camp.app.sns.service.SnsVO;

//@SessionAttributes
@RestController
@CrossOrigin(originPatterns = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.PUT })
@RequestMapping("/java")
public class SnsController {
	private String imagePath = "/home/upload/sns/";
//	private String imagePath = "d:\\upload\\sns\\";
	
	@Autowired
	SnsService service;
	@Autowired
	SnsCommentService scService;

	// 전체조회
	@GetMapping("/getSnsList")
	public List<SnsVO> getSnsList() {
		return service.getSnsList();

	}

	// 페이징
	@GetMapping("/sns/{page}")
	public List<SnsImageVO> getSnsList(@PathVariable("page") int page) {
		return service.showSnsByPage(page);
	}

	// 검색 해시태그
	@GetMapping("/hashtag")
	public List<String> getHashtag() {
		return service.getHashTag();
	}

	// 검색 닉네임
	@GetMapping("/snsnickname")
	public List<String> getNickname() {
		return service.getNickName();
	}

//	//글쓰기
//	@PostMapping("/sns")
//	public boolean insert(@RequestBody SnsVO sns) {
//		int result = service.insertSns(sns);
//		if(result > 0) {
//			return true;
//		} else {
//			return false;
//		}
////		System.out.println(sns);
//	}

	// 단건조회
//	@GetMapping("/snsGet/{writeNo}")
//	public SnsVO getSns(@PathVariable int writeNo) {
//		return service.getSns(writeNo);
//	}

	// 글수정
	@PutMapping("/snsUpdate")
	public void snsUpdate(@RequestBody SnsVO vo) {
		service.snsUpdate(vo);
	}

	// 글삭제
	@DeleteMapping("/snsDelete")
	public void snsDelete(@RequestParam int writeNo) {
//		System.out.println(writeNo);
		service.snsDelete(writeNo);
	}

	//admin 삭제
	@DeleteMapping("/snsDeleteByAdmin")
	public void snsDeleteByAdmin(@RequestParam int writeNo) {
//		System.out.println(writeNo);
		service.snsDeleteByAdmin(writeNo);
	}
	/// 이미지
	// 글 등록
	@PostMapping("/sns")
	public boolean addSns(InputSnsVO sns) {
		System.out.println(sns);
		return service.addSns(sns);
//		return true;
	}

	// sns 개별조회 - 텍스트
	@GetMapping("/snsDetail/{writeNo}")
	public SnsVO showSnsOne(@PathVariable int writeNo) {
		System.out.println(writeNo);
		return service.showSnsOne(writeNo);
	}

	// sns 이미지에 대한 정보
	@GetMapping("/snsImage/{writeNo}")
	public List<SnsImageVO> getSnsImageList(@PathVariable("writeNo") int writeNo) {
		return service.showSnsImageByWriteNo(writeNo);
	}

	// 이미지 (jpg 등 불러오기)
	@GetMapping("/showSnsImage/{imagePath}/{storedName}")
	public ResponseEntity<Resource> showImage(@PathVariable String imagePath, @PathVariable String storedName) {
		String fullPath = this.imagePath+ imagePath + "/" + storedName;
		System.out.println("*** FullPath : " + fullPath);
		Resource resource = new FileSystemResource(fullPath);

		
		if(!resource.exists()) {
			System.out.println("File Not Found ! ");
			fullPath = this.imagePath + "default.png";
			resource = new FileSystemResource(fullPath);
			if(!resource.exists()) {
				return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
			}
		}

		HttpHeaders header = new HttpHeaders();
		Path filePath = null;

		try {
			filePath = Paths.get(fullPath);
			header.add("Content-Type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}

	// 댓글 등록
	@PostMapping("/comment")
	public boolean insertSnsComment(@RequestBody SnsCommentVO snsComment) {
		System.out.println(snsComment);
//		return true;
		int result = scService.addSnsComment(snsComment);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 게시글별 댓글 전체 출력
	@GetMapping("/snsComment/{writeNo}")
	public List<SnsCommentVO> getSnsCommentList(@PathVariable("writeNo") int writeNo) {
		SnsCommentVO vo = new SnsCommentVO();
		System.out.println(writeNo);
		System.out.println(vo);
		return scService.findByWriteNoToSnsComment(writeNo);
	}

	// 유저가 작성한 총게시글 수
	@GetMapping("/memberCount/{email}")
	public int getCountSnsByUser(@PathVariable("email") String email) {
		System.out.println(email);
		return service.countSnsByUser(email);
	}

	///// 유저가 작성한 게시글 리스트 출력(위의 전체이미지 불러오기에서 가져옴)
	// 본인 ... 글 이미지 정보 가져오기
	@GetMapping("/memberSnsList/{email}/{page}")
	public List<SnsImageVO> getSnsListByUser(@PathVariable("email") String email, @PathVariable("page") int page) {
		return service.showSnsByPageByUser(email, page);
	}

	// 게시물 좋아요 insert
	@PostMapping("/like")
	public boolean insertSnsLike(@RequestBody MySnsLikeVO snsLike) {
		System.out.println(snsLike);
		return true;
//		int result = scService.addSnsComment(snsComment);
//		if (result > 0) {
//			return true;
//		} else {
//			return false;
//		}
	}

	// 유저가 좋아요한 총게시글 수
	@GetMapping("/memberLikeCount/{email}")
	public int getLikeCountSnsByUser(@PathVariable("email") String email) {
		System.out.println(email);
		return service.countLikeSnsByUser(email);
	}

	// 유저가 좋아요한 게시글 리스트 출력
	@GetMapping("/memberSnsLikeList/{email}/{page}")
	public List<SnsImageVO> getSnsLikeListByUser(@PathVariable("email") String email, @PathVariable("page") int page) {
		return service.showSnsLikeByPageByUser(email, page);
	}
	
	//해시태그검색한 게시글 이미지리스트 출력
	@GetMapping("/hashtagList/{hashtag}/{page}")
	public List<SnsImageVO> getSnsListByHashtag(@PathVariable("hashtag") String hashtag, @PathVariable("page") int page) {
		System.out.println(hashtag +","+ page);
		return service.showSnsByPageByHashtag(hashtag, page);
	}
	
	//해시태그당한 페이지!
	// 유저가 해시태그당한 총게시글 수
	@GetMapping("/memberTagCount/{nickname}")
	public int getTagCountSnsByUser(@PathVariable("nickname") String nickname) {
		System.out.println(nickname);
		return service.countTagSnsByUser(nickname);
	}

	// 유저가 해시태그당한 게시글 리스트 출력
	@GetMapping("/memberSnsTagList/{nickname}/{page}")
	public List<SnsImageVO> getSnsTagListByUser(@PathVariable("nickname") String nickname, @PathVariable("page") int page) {
		return service.showSnsTagByPageByUser(nickname, page);
	}

}
