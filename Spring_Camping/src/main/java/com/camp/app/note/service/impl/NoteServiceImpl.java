package com.camp.app.note.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.note.mapper.NoteMapper;
import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;

@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	NoteMapper mapper;
	
	@Override
	public int writeContents(NoteVO nvo) {
		nvo.setNoteId(mapper.getMaxNoteId());
		
		String temp = "";
		
		for(int i=0; i<nvo.getNoteContents().size(); i++) {
			temp += nvo.getNoteContents().get(i);
		}
	 
		return mapper.writeCotents(nvo);	
	}

}
