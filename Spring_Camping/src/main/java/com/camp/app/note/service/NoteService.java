package com.camp.app.note.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface NoteService {
	//내 리스트 가져오기
	public List<NoteVO> getMyNoteList(String email);
//	
	//invited_user update
	public boolean inviteUser(InvitedMemberVO ivo);
	
	//초대받은 노트 리스트
	public List<NoteVO> showInvitedNoteList(String email);
	
	//노트insert
	public boolean writeContents(NoteVO nvo, List<MultipartFile> files);
	
	//노트update
	public boolean updateContents(NoteVO nvo, List<MultipartFile> files);
	
	//공유중인 맴버 리스트
	public String showInvitedMember(int noteId);
	
	//공유 끊기
	public void delMember(InvitedMemberVO ivo);
	
	//노트삭제
	public void deleteNote(NoteDto dto);
	
	//GoMyNote
	public NoteVO getMyNote(int noteId);

	
}
