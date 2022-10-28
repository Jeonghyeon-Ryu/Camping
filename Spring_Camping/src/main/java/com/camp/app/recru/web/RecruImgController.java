package com.camp.app.recru.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.recru.service.RecruImgService;
import com.camp.app.recru.service.RecruImgVO;
import com.camp.app.recru.service.RecruService;

@CrossOrigin(origins = "*")
@RequestMapping("/java")
@RestController
public class RecruImgController {

	@Autowired
	RecruImgService imgService;

	@Autowired
	RecruService recruService;

	// 이미지 저장
	@PostMapping("/recruImg")
	public boolean imgInsert(@RequestParam List<MultipartFile> files) throws IOException {
		return imgService.insertRecruImg(files);
	}

	// 이미지 정보 조회
	@GetMapping("/recruImg/{recruId}")
	public List<RecruImgVO> findRecruImg(@PathVariable int recruId) {
		return imgService.findImg(recruId);
	}

	// 이미지 출력
	@GetMapping("/recruImg/{imgPath}/{storedName}")
	public ResponseEntity<Resource> showImage(@PathVariable String imgPath, @PathVariable String storedName) {
		String fullPath = "/home/upload/recru/" + imgPath + "\\" + storedName;
		System.out.println("*** FullPath : " + fullPath);
		Resource resource = new FileSystemResource(fullPath);

		if (!resource.exists()) {
			System.out.println("File Not Found ! ");
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
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

	// 이미지 수정
	@PostMapping(value="/recruImg/update")
	public int UpdateImage(List<MultipartFile> files,RecruImgVO imgVO){
		int id = imgVO.getRecruId();
		System.out.println("게시글 아이디  : " +id);
		//새로 추가하는 파일
		if(files!=null) {
			imgService.addRecruImg(files,imgVO.getRecruId());			
		}
		// 삭제하는 파일
		if(imgVO.getImgList()!=null) {
			String[] list = imgVO.getImgList();
			imgService.updateRecruImg(list, imgVO.getRecruId());
		}
		return 1;
		
	}
}
