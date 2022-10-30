package com.camp.app.note.service.impl;

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

import com.camp.app.note.mapper.NoteImgMapper;
import com.camp.app.note.mapper.NoteMapper;
import com.camp.app.note.service.NoteImgService;
import com.camp.app.note.service.NoteImgVO;
import com.camp.app.note.service.NoteVO;


@Service
public class NoteImgServiceImpl implements NoteImgService{
	//이미지저장
	//private String imagePath = "/home/upload/note/";
	//집에서 밑에꺼 주석풀기 위에꺼는 주석막기
	private String imagePath = "c:\\upload\\note\\";
	@Autowired
	NoteImgMapper imgMapper;
	@Autowired
	NoteMapper mapper;
	
		@Override
		public boolean insertImg(List<MultipartFile> files) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String directoryPath = sdf.format(date);
			String uploadPath = this.imagePath+directoryPath;
			
			System.out.println("여기");
			System.out.println(files);
			
			//폴더 주소
			File uploadPathDir = new File(uploadPath);
			if(!uploadPathDir.exists()) {
				uploadPathDir.mkdirs();
			}
			files.forEach(file->{
				NoteVO vo = new NoteVO();
				NoteImgVO img = new NoteImgVO();
				img.setOriginName(file.getOriginalFilename());
				img.setImgFormat(img.getOriginName().substring(img.getOriginName().indexOf('.')+1));
				img.setImgSize(file.getSize());
				img.setImgPath(directoryPath);
				img.setNoteId(imgMapper.getMaxNoteId());
				
				
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

		@Override
		public List<NoteImgVO> findImg(int noteId) {
		List<NoteImgVO> temp =imgMapper.findNoteImgInfo(noteId);
		
			return imgMapper.findNoteImgInfo(noteId);
		}
		//src에 넣을 경로와 이름 가져오기
		@Override
		public ResponseEntity<Resource> showImg(String imgPath, String storedName) {
			String fullPath =  this.imagePath + imgPath + "\\" + storedName;
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
}