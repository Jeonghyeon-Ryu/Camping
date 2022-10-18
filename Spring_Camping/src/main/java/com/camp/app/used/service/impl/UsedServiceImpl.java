package com.camp.app.used.service.impl;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.used.mapper.UsedMapper;
import com.camp.app.used.service.InputUsedVO;
import com.camp.app.used.service.UsedImageVO;
import com.camp.app.used.service.UsedReviewVO;
import com.camp.app.used.service.UsedService;
import com.camp.app.used.service.UsedVO;

@Service
public class UsedServiceImpl implements UsedService {
	
	@Autowired
	UsedMapper mapper;

	//지워도될듯???
	@Override
	public int getUsedId() {
		return mapper.getUsedId();
	}

	//등록
	@Override
	public boolean insertUsed(InputUsedVO used) {
		UsedVO resultUsed = new UsedVO();
		resultUsed.setUsedId(mapper.findMaxByUsedId()+1);
		resultUsed.setUsedName(used.getUsedName());
		resultUsed.setUsedPrice(used.getUsedPrice());
		resultUsed.setUsedCategory(used.getUsedCategory());
		resultUsed.setUsedCondition(used.getUsedCondition());
		resultUsed.setUsedContent(used.getUsedContent());
		resultUsed.setNickName(used.getNickName());
		resultUsed.setEmail(used.getEmail());
		
		
		//이거뭐하는코드?
		String usedPlace="";
		for(int i=0; i<used.getUsedPlace().size(); i++) {
			if(usedPlace == "") {
				usedPlace = used.getUsedPlace().get(i);
			}else {
				usedPlace = usedPlace + " " + used.getUsedPlace().get(i);
			}
		}
		
		resultUsed.setUsedPlace(usedPlace);
		mapper.insertUsed(resultUsed);
		
		//이미지 저장 시작
		List<MultipartFile> files = used.getFiles();
		
		//경로설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = "d:\\upload\\used\\" + directoryPath;
		File uploadPathDir = new File(uploadPath);
		if(!uploadPathDir.exists()) {
			uploadPathDir.mkdirs();
		}

		files.forEach(file->{
			UsedImageVO image = new UsedImageVO();
			image.setUsedImageId(mapper.findMaxByUsedImageId()+1);
			image.setUsedOriginName(file.getOriginalFilename());
			
			image.setUsedFormat(image.getUsedOriginName().substring(image.getUsedOriginName().lastIndexOf("."), image.getUsedOriginName().length()));
			image.setUsedSize(file.getSize());
			image.setUsedPath(directoryPath);
			image.setUsedId(resultUsed.getUsedId());
			
			UUID uuid = UUID.randomUUID();
			String[] uuids = uuid.toString().split("-");
			image.setUsedStoredName(uuids[0] + "_" + image.getUsedOriginName());
			
			System.out.println(image);
			File resultFile = new File(uploadPath,image.getUsedStoredName());
			try {
				System.out.println(file);
				file.transferTo(resultFile);
				// 문제가 있음. 제대로 들어갔는지 확인이 안됨.
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mapper.insertUsedImage(image);
		});
		 return true;
	}
	
	@Override
	public int getMaxUsedImageId() {
		return mapper.findMaxByUsedImageId();
	}
	
	//전체조회
	@Override
	public List<UsedVO> selectAllUsedList() {
		return mapper.selectAllUsedList();
	}

	//단건조회
	@Override
	public UsedVO findDetail(int usedId) {
		return mapper.findDetail(usedId);
	}
	
	//내가쓴글조회
	public List<UsedVO> findMyUsed(String email){
		return mapper.findMyUsed(email);
	}
	 
	//수정
	@Override
	public void updateUsed(UsedVO usedVO) {
		mapper.updateUsed(usedVO);
	}

	//거래상태변경
	@Override
	public int updateDealStatus(int dealStatus) {
		return mapper.updateDealStatus(dealStatus);
	}
	
	//접근제한
	@Override
	public void restrictUsed(UsedVO used) {
		mapper.restrictUsed(used);
	}
	
	
	//삭제
	@Override
	public void deleteUsed(UsedVO used) {
		mapper.deleteUsed(used);
	}

	//필터검색
	@Override
	public List<UsedVO> searchUsedList() {
		// TODO Auto-generated method stub
		return null;
	}

	//키워드검색
	@Override
	public List<UsedVO> findUsedKeyword(String keyword) {
		return mapper.findUsedKeyword(keyword);
	}

	//게시글상태변경
	@Override
	public int updateRestrict() {
		// TODO Auto-generated method stub
		return 1;
	}

	//조회수증가
	@Override
	public int updateCnt(int usedId) {
		return mapper.updateCnt(usedId);
	}

	//좋아요증가
	@Override
	public int updateLike() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxUsedId() {
		return mapper.findMaxByUsedId();
	}

	//사진보여주기
	@Override
	public List<UsedImageVO> showUsedImageByUsedId(int usedId) {
		return mapper.findByUsedIdToUsedImage(usedId);
	}

	//리뷰등록
	@Override
	public int insertReview(UsedReviewVO review) {
		 return mapper.insertReview(review);
	}



}
