package com.camp.app.recru.web;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.recru.service.RecruImgService;
import com.camp.app.recru.service.RecruImgVO;
import com.camp.app.recru.service.RecruService;

@CrossOrigin(origins = "*")
@RestController
public class RecruImgController {

	@Autowired
	RecruImgService imgService;
	
	@Autowired
	RecruService recruService;
	
	//이미지 저장
	@PostMapping("/recruImg")
	public boolean imgInsert(@RequestParam List<MultipartFile> files) throws IOException {
		return imgService.insertRecruImg(files);
	}
	
	//이미지 다운로드
	@GetMapping("/recruImg/{recruId}")
	public List<RecruImgVO> findRecruImg(@PathVariable int recruId){
		
		return null;
	}
	
	
	

}
