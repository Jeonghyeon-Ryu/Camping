package com.camp.app.note.mapper;



import java.util.List;

import org.springframework.stereotype.Component;


import com.camp.app.note.service.NoteVO;
@Component
public interface NoteMapper {
	
	public int getMaxNoteId();
	
	//내가 작성한 노션 조회
	public List<NoteVO> getMyNoteList(String email);

//	//초대받은 노션 조회
//	public List<NoteVO> getInvitedList(NoteVO nvo);

	
	//노트 게시글 저장
	public int writeCotents(NoteVO nvo);
	
	//노트삭제
	public int deleteNote(int noteId);
	
	//노트번호로 노트내용 가져오기
	public NoteVO getMyNoteInfo(int noteId);
}
