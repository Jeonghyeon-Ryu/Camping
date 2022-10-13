package com.camp.app.camp.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.camp.mapper.CampMapper;
import com.camp.app.camp.service.CampImageVO;
import com.camp.app.camp.service.CampService;
import com.camp.app.camp.service.CampVO;
import com.camp.app.camp.service.InputCampVO;

@Service
public class CampServiceImpl implements CampService{

	@Autowired
	CampMapper mapper;
	
	@Override
	public List<CampVO> showCampAll() {
		List<CampVO> campList = mapper.findAll();
		return null;
	}
	@Override
	public List<CampVO> showCampByPage(int page){
		return mapper.findByPage(page);
	}
	@Override
	public int count() {
		return mapper.count();
	}
	
	@Override
	public int getMaxCampId() {
		return mapper.findMaxByCampId();
	}
	
	@Override
	public CampVO showCampOne(int campId) {
		return mapper.findByCampId(campId);
	}
	
	@Transactional
	@Override
	public boolean addCamp(InputCampVO camp) {
		CampVO resultCamp = new CampVO();
		resultCamp.setCampId(mapper.findMaxByCampId()+1);
		resultCamp.setCampAddress(camp.getCampAddress());
		resultCamp.setCampName(camp.getCampName());
		resultCamp.setCampPrice(camp.getCampPrice());
		resultCamp.setCampSite(camp.getCampSite());
		String campInfo="";
		for(int i=0; i<camp.getCampInfo().size(); i++) {
			if(campInfo == "") {
				campInfo = camp.getCampInfo().get(i);
			} else {
				campInfo = campInfo + " " + camp.getCampInfo().get(i);
			}
		}
		resultCamp.setCampInfo(campInfo);
		mapper.insertCamp(resultCamp);
		
//		이미지 저장 시작
		List<MultipartFile> files = camp.getFiles();
		
//		경로 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = "d:\\upload\\camp\\" + directoryPath;
		File uploadPathDir = new File(uploadPath);
		if(!uploadPathDir.exists()) {
			uploadPathDir.mkdirs();
		}

		files.forEach(file->{
			CampImageVO image = new CampImageVO();
			image.setCampImageId(mapper.findMaxByCampImageId()+1);
			image.setOriginName(file.getOriginalFilename());
			
			image.setImageFormat(image.getOriginName().substring(image.getOriginName().lastIndexOf("."), image.getOriginName().length()));
			image.setImageSize(file.getSize());
			image.setImagePath(directoryPath);
			image.setCampId(resultCamp.getCampId());
			
			UUID uuid = UUID.randomUUID();
			String[] uuids = uuid.toString().split("-");
			image.setStoredName(uuids[0] + "_" + image.getOriginName());
			
			File resultFile = new File(uploadPath,image.getStoredName());
			try {
				file.transferTo(resultFile);
				// 문제가 있음. 제대로 들어갔는지 확인이 안됨.
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mapper.insertCampImage(image);
		});
		return true;
	}

	@Override
	public int getMaxCampImageId() {
		return mapper.findMaxByCampImageId();
	}
	@Override
	public List<CampImageVO> showCampImageByCampId(int campId) {
		return mapper.findByCampIdToCampImage(campId);
	}

}
