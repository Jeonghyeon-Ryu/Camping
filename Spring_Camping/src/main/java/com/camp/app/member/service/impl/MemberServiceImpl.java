package com.camp.app.member.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.member.mapper.MemberMapper;
import com.camp.app.member.service.MemberService;
import com.camp.app.member.service.MemberVO;
import com.camp.app.member.service.ProfileImageVO;

@Service
public class MemberServiceImpl implements MemberService{
	private String imagePath = "/home/upload/member/";
//	private String imagePath = "d:\\upload\\member\\";
	@Autowired
	MemberMapper mapper;
	
	// show add modify remove ...
	// 회원 목록 보기 ( 관리자 )
	public List<MemberVO> showAllMember() {
		return mapper.findAll();
	}
	
	// 회원 제한 ( 관리자 )
	public int restrictMember(MemberVO member) {
		member.setStatus(1);
		return mapper.update(member);
	}
	
	// 회원 가입
	@Override
	public int signup(MemberVO member) {
		return mapper.insert(member);
	}
	
	// 회원 가입 이메일 확인
	@Override
	public boolean checkEmail(String email) {
		return mapper.existsByEmail(email);
	}
	
	// 회원 가입 닉네임 확인
	@Override
	public boolean checkNickname(String nickname) {
		return mapper.existsByNickname(nickname);
	}
	
	// 회원 정보 수정
	@Override
	public int modifyMember(MemberVO member, MultipartFile file) {
		
		int result = mapper.update(member);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = this.imagePath + directoryPath;
		File uploadPathDir = new File(uploadPath);
		if(!uploadPathDir.exists()) {
			uploadPathDir.mkdirs();
		}
		if(file != null && file.getSize()!=0) {
			ProfileImageVO image = new ProfileImageVO();
			image.setProfileImageId(mapper.findMaxByProfileImageId()+1);
			image.setOriginName(file.getOriginalFilename());
			
			image.setImageFormat(image.getOriginName().substring(image.getOriginName().lastIndexOf("."), image.getOriginName().length()));
			image.setImageSize(file.getSize());
			image.setImagePath(directoryPath);
			image.setEmail(member.getEmail());
			
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
			result += mapper.insertProfileImage(image);
			System.out.println("Image Info : " + image);
		}
		return result;
	}
	
	// 회원 비밀번호 변경
	@Override
	public int modifyPassword(MemberVO member) {
		return mapper.update(member);
	}
	
	// 회원 탈퇴
	@Override
	public int signout(MemberVO member) {
		member.setStatus(0);
		return mapper.update(member);
	}

	@Override
	public MemberVO login(MemberVO member) {
		return mapper.findByEmailAndPassword(member);
	}

	@Override
	public MemberVO findByEmail(String memberId) {
		return mapper.findByEmail(memberId);
	}

	// 프로필 사진 정보 가져오기
	@Override
	public ProfileImageVO showProfileImage(String email) {
		return mapper.findByEmailToProfileImage(email);
	}

	@Override
	public ResponseEntity<Resource> showImage(String imagePath, String storedName) {
		String fullPath = this.imagePath + imagePath + "/" + storedName;
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
			header.add("Content-Type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}

	//닉네임으로 이메일찾기
	@Override
	public MemberVO findByNickname(String nickname) {
		MemberVO member = mapper.findByNickname(nickname);	//DB에 한번 접속
		MemberVO result = new MemberVO();
		result.setNickname(member.getNickname());
		result.setProfileInfo(member.getProfileInfo());
		result.setEmail(member.getEmail());
		return result;
	}

	@Override
	public MemberVO findBySocialEmail(String socialEmail) {
		return mapper.findBySocialEmail(socialEmail);
	}
}
