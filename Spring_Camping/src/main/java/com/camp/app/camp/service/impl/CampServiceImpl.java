package com.camp.app.camp.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.camp.mapper.CampMapper;
import com.camp.app.camp.service.CampImageVO;
import com.camp.app.camp.service.CampModifyVO;
import com.camp.app.camp.service.CampService;
import com.camp.app.camp.service.CampVO;
import com.camp.app.camp.service.InputCampVO;
import com.camp.app.report.mapper.ReportMapper;
import com.camp.app.report.service.ReportVO;
import com.camp.app.save.mapper.SaveMapper;
import com.camp.app.save.service.SaveVO;

@Service
public class CampServiceImpl implements CampService{
	private String imagePath = "/home/upload/camp/";
//	private String imagePath = "d:\\upload\\camp\\";
	@Autowired
	CampMapper mapper;
	@Autowired
	ReportMapper reportMapper;
	@Autowired
	SaveMapper saveMapper;
	
	@Override
	public List<CampVO> showCampAll() {
		List<CampVO> campList = mapper.findAll();
		return campList;
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
		String uploadPath = this.imagePath + directoryPath;
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
	
//	캠핑장 수정
	
	@Transactional
	@Override
	public boolean modifyCamp(InputCampVO camp) {
		CampModifyVO resultCamp = new CampModifyVO();
		resultCamp.setCampModifyId(mapper.findMaxByCampModifyId()+1);
		resultCamp.setCampId(camp.getCampId());
		resultCamp.setCampAddress(camp.getCampAddress());
		resultCamp.setCampName(camp.getCampName());
		resultCamp.setCampPrice(camp.getCampPrice());
		resultCamp.setCampSite(camp.getCampSite());
		resultCamp.setEmail(camp.getEmail());
		String campInfo="";
		for(int i=0; i<camp.getCampInfo().size(); i++) {
			if(campInfo == "") {
				campInfo = camp.getCampInfo().get(i);
			} else {
				campInfo = campInfo + " " + camp.getCampInfo().get(i);
			}
		}
		resultCamp.setCampInfo(campInfo);
		mapper.insertCampModify(resultCamp);
		
		ReportVO report = new ReportVO();
		report.setReportId(reportMapper.findMaxByReportId()+1);
		report.setBoardId(camp.getCampId());
		report.setEmail(camp.getEmail());
		report.setReportContent("캠핑장 수정요청");
		report.setBoardDivision(0);		// 캠핑장
		report.setStatus(0);			// 미처리
		
		reportMapper.insert(report);
		
//		이미지 저장 시작
		List<MultipartFile> files = camp.getFiles();
		
//		경로 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = this.imagePath + directoryPath;
		File uploadPathDir = new File(uploadPath);
		if(!uploadPathDir.exists()) {
			uploadPathDir.mkdirs();
		}
		
		files.forEach(file->{
			if(file.getSize()!=0) {
				CampImageVO image = new CampImageVO();
				image.setCampImageId(mapper.findMaxByCampModifyImageId()+1);
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
				mapper.insertCampModifyImage(image);
				System.out.println("Image Info : " + image);
			}
		});
		System.out.println("Camp Info : " + resultCamp);
		System.out.println("Report Info : " + report);
		return true;
	}
	@Override
	public int getMaxCampModifyId() {
		return mapper.findMaxByCampModifyId();
	}
	@Override
	public int isCampModifying(int campId) {
		return mapper.countCampModifyByCampId(campId);
		
	}
	
//	캠핑 저장 목록
	@Override
	public List<CampVO> showStoredCamp(String email) {
		SaveVO save = new SaveVO();
		save.setBoardDivision(0);
		save.setEmail(email);
		List<SaveVO> saveList = saveMapper.findByEmailAndBoardDivision(save);
		List<CampVO> campList = new ArrayList<>();
		saveList.forEach(saveOne -> {
			campList.add(mapper.findByCampId(saveOne.getBoardId()));
		});
		return campList;
	}
	@Override
	public boolean modifyCampByAdmin(InputCampVO camp) {
		CampVO resultCamp = new CampVO();
		resultCamp.setCampId(camp.getCampId());
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
		mapper.update(resultCamp);
		
//		이미지 저장 시작
		List<MultipartFile> files = camp.getFiles();
		
//		경로 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = this.imagePath + directoryPath;
		File uploadPathDir = new File(uploadPath);
		if(!uploadPathDir.exists()) {
			uploadPathDir.mkdirs();
		}
		
		files.forEach(file->{
			if(file.getSize()!=0) {
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
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				mapper.insertCampImage(image);
			}
		});
		System.out.println("Camp Info : " + resultCamp);
		return true;
	}

}
