package com.camp.app.sns.service.impl;

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

import com.camp.app.sns.mapper.SnsMapper;
import com.camp.app.sns.service.SnsImageVO;
import com.camp.app.sns.service.SnsService;
import com.camp.app.sns.service.SnsVO;
import com.camp.app.sns.service.InputSnsVO;

@Service
public class SnsServiceImpl implements SnsService {

	@Autowired
	SnsMapper mapper;

	// 텍스트 출력
	@Override
	public List<SnsVO> getSnsList() {
		return mapper.getSnsList();
	}

	

	@Override
	public List<String> getHashTag() {
		List<String> hashTag = mapper.getHashTag();
		return hashTag;
	}

	@Override
	public List<String> getNickName() {
		List<String> nickName = mapper.getNickName();
		return nickName;
	}

	@Override
	public int insertSns(SnsVO vo) {
		return mapper.insertSns(vo);
	}

//	@Override
//	public SnsVO getSns(int writeNo) {
//		return mapper.getSns(writeNo);
//	}

	@Override
	public void snsUpdate(SnsVO vo) {
		mapper.snsUpdate(vo);
	}

	@Override
	public void snsDelete(int writeNo) {
		mapper.snsDelete(writeNo);
	}

////////////////////////////

	// 전체출력인듯..?(이미지+텍스트)
	@Override
	public List<SnsVO> showSnsAll() {
		List<SnsVO> snsList = mapper.getSnsList();
		return null;
	}
	
	// 페이징
	@Override
	public List<SnsImageVO> showSnsByPage(int page) {
		return mapper.findByPage(page);
	}
	
	@Override
	public int count() {
		return mapper.count();
	}

	@Override
	public int findMaxByWriteNo() {
		return mapper.findMaxByWriteNo();
	}
	
	// sns 개별조회 - 텍스트
	@Override
	public SnsVO showSnsOne(int writeNo) {
		return mapper.findByWriteNo(writeNo);
	}
	
	// sns 개별조회 - 이미지 리스트
		@Override
		public List<SnsImageVO> showSnsImageByWriteNo(int writeNo) {
			return mapper.findByWriteNoToSnsImage(writeNo);
		}

	// sns 이미지 등록
	@Transactional
	@Override
	public boolean addSns(InputSnsVO sns) {
		SnsVO resultSns = new SnsVO();
		resultSns.setWriteNo(mapper.findMaxByWriteNo() + 1);
		resultSns.setContent(sns.getContent());
		resultSns.setLocation(sns.getLocation());
		resultSns.setPlace(sns.getPlace());
		resultSns.setHashtag(sns.getHashtag());
		resultSns.setWriteDate(sns.getWriteDate());
		resultSns.setStatus(sns.getStatus());
		resultSns.setImplement(sns.getImplement());
		resultSns.setNickname(sns.getNickname());

		mapper.insertSns(resultSns);

//			이미지 저장 시작
		List<MultipartFile> files = sns.getFiles();

//			경로 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = "d:\\upload\\sns\\" + directoryPath;
		File uploadPathDir = new File(uploadPath);
		if (!uploadPathDir.exists()) {
			uploadPathDir.mkdirs();
		}

		files.forEach(file -> {
			SnsImageVO image = new SnsImageVO();
			image.setSnsImageNo(mapper.findMaxBySnsImageNo() + 1);
			image.setOriginName(file.getOriginalFilename());
			image.setSnsFormat(image.getOriginName().substring(image.getOriginName().lastIndexOf("."),
					image.getOriginName().length()));
			image.setSnsSize(file.getSize());
			image.setSnsPath(directoryPath);
			image.setWriteNo(resultSns.getWriteNo());

			UUID uuid = UUID.randomUUID();
			String[] uuids = uuid.toString().split("-");
			image.setStoredName(uuids[0] + "_" + image.getOriginName());

			File resultFile = new File(uploadPath, image.getStoredName());
			try {
				// 이미지 저장
				file.transferTo(resultFile);
				// 문제가 있음. 제대로 들어갔는지 확인이 안됨.
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mapper.insertSnsImage(image);
		});
		return true;
	}

	@Override
	public int findMaxBySnsImageNo() {
		return mapper.findMaxBySnsImageNo();
	}
	
}
