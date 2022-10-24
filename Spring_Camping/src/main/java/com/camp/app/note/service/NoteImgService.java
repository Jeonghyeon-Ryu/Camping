package com.camp.app.note.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface NoteImgService {
	//이미지 저장
	public boolean insertImg(List<MultipartFile> files);
	
	//이미지 정보 조회
	public List<NoteImgVO> findImg(int noteId);
	
	//이미지 불러오기(사진 정보 가져오기)
	public ResponseEntity<Resource> showImg(String imgPath, String storedName);
	
	
}
