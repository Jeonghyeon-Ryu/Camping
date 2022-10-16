package com.camp.app.note.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface NoteService {
	//mynoteList
	public List<NoteVO> getMyNoteList(String email);
//	
//	//invitedNote
//	public List<NoteVO> getInvitedList(NoteVO nvo);
	
	//writeNote
	public int writeContents(NoteVO nvo);
	
	//deleteNote
	public void deleteNote(NoteDto dto);
	
	//Mynote
//	public void selectOne(int noteId);
	
	//GoMyNote
	public NoteVO getMyNote(int noteId);
}
