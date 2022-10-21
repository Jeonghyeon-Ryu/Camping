package com.camp.app.note.service.impl;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.note.mapper.NoteImgMapper;
import com.camp.app.note.mapper.NoteMapper;
import com.camp.app.note.service.NoteDto;
import com.camp.app.note.service.NoteImgVO;
import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;


@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	NoteMapper mapper;
	@Autowired
	NoteImgMapper imgMapper;
	
	//Note Insert
	@Override
	public boolean writeContents(NoteVO nvo, List<MultipartFile> files) {
		
		String noteContent = "";
		
		for(int i=0; i<nvo.getNoteContents().size(); i++) {
			noteContent += nvo.getNoteContents().get(i) + "|||||||||";
		};
		
		nvo.setNoteContent(noteContent);
		nvo.setNoteId(mapper.getMaxNoteId());
		
		mapper.writeCotents(nvo);
		
		System.out.println(nvo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = "c:\\upload\\recru\\"+directoryPath;
		//폴더 주소
		if(files.size() !=0) {
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
			img.setNoteId(nvo.getNoteId());
			
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
		}
		return true;
	}
	//MynoteList
	@Override
	public List<NoteVO> getMyNoteList(String email) {
		List<NoteVO> listInfo = mapper.getMyNoteList(email);
		return listInfo;
	}
	@Override
	public void deleteNote(NoteDto dto) {
		for(int i=0; i<dto.getNoteIds().size(); i++) { 
			mapper.deleteNote(dto.getNoteIds().get(i));
		}
	}
	@Override
	public NoteVO getMyNote(int noteId) {
		
		NoteVO nvo = mapper.getMyNoteInfo(noteId);
		
		String temp = nvo.getNoteContent();
		
		List<String> tempList = new ArrayList<>();
		
		while(temp.indexOf("|||||||||") != -1) {
			tempList.add(temp.substring(0, temp.indexOf("|||||||||")));
			
			temp = temp.substring(temp.indexOf("|||||||||")+9, temp.length());
		};
	
		nvo.setNoteContents(tempList);
		
		nvo.getNoteContents().forEach(s->System.out.println("*********"+s));
		
		
		return nvo;
	
	}

}
