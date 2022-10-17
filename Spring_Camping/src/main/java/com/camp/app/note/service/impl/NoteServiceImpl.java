package com.camp.app.note.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.note.mapper.NoteMapper;
import com.camp.app.note.service.NoteDto;
import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;

@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	NoteMapper mapper;
	
	//Note Insert
	@Override
	public int writeContents(NoteVO nvo) {
		
		String noteContent = "";
		
		for(int i=0; i<nvo.getNoteContents().size(); i++) {
			noteContent += nvo.getNoteContents().get(i) + "|||||||||";
		};
		
		nvo.setNoteContent(noteContent);
		nvo.setNoteId(mapper.getMaxNoteId());
		return mapper.writeCotents(nvo);	
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
