package com.camp.app.note.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.note.mapper.NoteImgMapper;
import com.camp.app.note.mapper.NoteMapper;
import com.camp.app.note.service.NoteImgService;
import com.camp.app.note.service.NoteImgVO;


@Service
public class NoteImgServiceImpl implements NoteImgService{
	//이미지저장
		
	@Autowired
	NoteImgMapper imgMapper;
	@Autowired
	NoteMapper mapper;
	
		@Override
		public boolean insertImg(List<MultipartFile> files) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String directoryPath = sdf.format(date);
			String uploadPath = "C:\\upload\\note\\"+directoryPath;
			
			
			//폴더 주소
			File uploadPathDir = new File(uploadPath);
			if(!uploadPathDir.exists()) {
				uploadPathDir.mkdirs();
			}
			files.forEach(file->{
				
				NoteImgVO img = new NoteImgVO();
				img.setOriginName(file.getOriginalFilename());
				img.setImgFormat(img.getOriginName().substring(img.getOriginName().indexOf('.')+1));
				img.setImgSize(file.getSize());
				img.setImgPath(directoryPath);
				img.setNoteId(mapper.getMaxNoteId());
				
				System.out.println(file);
				System.out.println(img);
				
				//uuid를 통한 랜덤으로 아이디 주기
				UUID uuid = UUID.randomUUID();
				String[] uuids = uuid.toString().split("-");
				img.setStoredName(uuids[0]+"_"+img.getOriginName());
				//업로드
				File uploadFile = new File(uploadPath, img.getStoredName());
				
				try {
					file.transferTo(uploadFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				imgMapper.insertNoteImg(img);
			});
			return true;	
		}
}