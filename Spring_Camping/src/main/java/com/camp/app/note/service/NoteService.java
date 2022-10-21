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
	public int writeContents(NoteVO nvo);
	
	//deleteNote
	public void deleteNote(NoteDto dto);
	
	//Mynote
//	public void selectOne(int noteId);
	
	//GoMyNote
	public NoteVO getMyNote(int noteId);
	//이미지 저장
	public boolean insertImg(List<MultipartFile> files);
}
