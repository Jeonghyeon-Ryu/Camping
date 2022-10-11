package com.camp.app.camp.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.camp.service.CampImageVO;
import com.camp.app.camp.service.CampService;
import com.camp.app.camp.service.CampVO;

@RestController
@CrossOrigin
public class CampController {

	@Autowired
	CampService service;
	
	@PostMapping("/camp")
	public boolean addCamp(@RequestBody CampVO camp) {
		return service.addCamp(camp);
	}

	@PostMapping("/campImage")
	public int addImage(@RequestParam List<MultipartFile> files) {
		List<CampImageVO> list = new ArrayList<>();
		System.out.println("Files : " + files);
		files.forEach(file->{
			CampImageVO image = new CampImageVO();
			image.setOriginName(file.getOriginalFilename());
			image.setFormat(image.getOriginName().substring(image.getOriginName().lastIndexOf("."), image.getOriginName().length()));
			image.setSize(file.getSize());
			
			UUID uuid = UUID.randomUUID();
			String[] uuids = uuid.toString().split("-");
			image.setStoredName(uuids[0]);
			list.add(image);
		});
		list.forEach(item -> System.out.println(item));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = "/home/ubuntu/upload/" + directoryPath;
		File resultPath = new File(uploadPath);
		if(!resultPath.exists()) {
			resultPath.mkdir();
		}
		
//		String fileRealName = imageDatas.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
//		long size = imageDatas.getSize(); //파일 사이즈
		
//		System.out.println("파일명 : "  + fileRealName);
//		System.out.println("용량크기(byte) : " + size);
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
//		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
//		String uploadFolder = "C:\\test\\upload";
		
		
		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */
		
//		
//		String uniqueName = uuids[0];
//		System.out.println("생성된 고유문자열" + uniqueName);
//		System.out.println("확장자명" + fileExtension);
		
		
		
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		
//		File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
//		try {
//			imageDatas.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return 1;
	}
}
