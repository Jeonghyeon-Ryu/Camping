package com.camp.app.note.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.note.mapper.NoteMapper;
import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;

@Service
public class NoteServcieImpl implements NoteService{

	
	@Autowired
	NoteMapper mapper;

	@Override
	public List<NoteVO> getMyNoteList(NoteVO nvo) {
		
		return mapper.getMyNoteList(nvo);
	}

	@Override
	public List<NoteVO> getInvitedList(NoteVO nvo) {
		
		return mapper.getInvitedList(nvo);
	}

	@Override
	public int writeContents(NoteVO nvo) {
		
		return mapper.writeCotents(nvo);
	}
	
	
	
}
