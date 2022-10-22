package com.camp.app.note.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface NoteImgService {
	//이미지 저장
	public boolean insertImg(List<MultipartFile> files);
}
