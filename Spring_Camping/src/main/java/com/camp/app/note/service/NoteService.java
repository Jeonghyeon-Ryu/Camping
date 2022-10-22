package com.camp.app.note.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface NoteService {
	//mynoteList
	public List<NoteVO> getMyNoteList(String email);
//	
//	//invitedNote
//	public List<NoteVO> getInvitedList(NoteVO nvo);
	
	//writeNote
	public boolean writeContents(NoteVO nvo, List<MultipartFile> files);
	
	//deleteNote
	public void deleteNote(NoteDto dto);
	
	//GoMyNote
	public NoteVO getMyNote(int noteId);
	
}
