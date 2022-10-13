package com.camp.app.note.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface NoteService {
	//mynoteList
	public List<NoteVO> getMyNoteList(NoteVO nvo);
	
	//invitedNote
	public List<NoteVO> getInvitedList(NoteVO nvo);
	
	//writeNote
	public int writeContents(NoteVO nvo);
	
}
