package com.camp.app.camp.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.camp.service.CampImageVO;
import com.camp.app.camp.service.CampService;
import com.camp.app.camp.service.CampVO;
import com.camp.app.camp.service.InputCampVO;

import retrofit2.http.POST;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/java")
public class CampController {
	private String imagePath = "/home/upload/camp/";
//	private String imagePath = "d:\\upload\\member\\";
	
	@Autowired
	CampService service;
	
	@PostMapping("/camp")
	public boolean addCamp(InputCampVO camp) {
		return service.addCamp(camp);
	}
	@GetMapping("/camp/{page}")
	public List<CampVO> getCampList(@PathVariable("page") int page){
		return service.showCampByPage(page);
	}
	
	@GetMapping("/campImage/{campId}")
	public List<CampImageVO> getCampImageList(@PathVariable("campId") int campId){
		return service.showCampImageByCampId(campId);
	}
	@GetMapping("/campDetail/{campId}")
	public CampVO showCampOne(@PathVariable int campId) {
		System.out.println(campId);
		return service.showCampOne(campId);
	}
	
	@GetMapping("/showImage/{imagePath}/{storedName}")
	public ResponseEntity<Resource> showImage(@PathVariable String imagePath, @PathVariable String storedName){
		String fullPath = this.imagePath + imagePath + "/" + storedName;
		System.out.println("*** FullPath : " +fullPath);
		Resource resource = new FileSystemResource(fullPath);
		
		if(!resource.exists()) {
			System.out.println("File Not Found ! ");
			fullPath = "/upload/default.png";
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
	@PostMapping("/campModify")
	public boolean modifyCamp(InputCampVO camp) {
		System.out.println(camp);
		return service.modifyCamp(camp);
	}
	
	
	@GetMapping("/campModify/{campId}")
	public boolean isCampModifying(@PathVariable int campId) {
		int result = service.isCampModifying(campId);
		if(result > 0) {
			return true; // 캠핑장 수정신청 존재
		} else {
			return false; // 캠핑장 수정신청 미존재
		}
		
	}
	
	@GetMapping("/savedCamp/{email}")
	public List<CampVO> findByEmailAndBoardDivision(@PathVariable String email){
		return service.showStoredCamp(email);
	}
	
	
//	관리자 캠핑장 수정
	@PostMapping("/admin/camp")
	public boolean modifyCampByAdmin(InputCampVO camp) {
		System.out.println("캠핑장 수정 관리자 : " + camp);
		return service.modifyCampByAdmin(camp);
	}
	
	// 캠핑장 총 수
	@GetMapping("/camp/endPage")
	public int getEndPage() {
		return service.count();
	}
	
//	캠핑장 탐색
	@GetMapping("/camp/name")
	public List<CampVO> findByCampName(String campName) {
		return service.findByCampName(campName);
	}
	
	@GetMapping("/camp/address")
	public List<CampVO> findByCampAddress(String campAddress) {
		return service.findByCampAddress(campAddress);
	}
}
