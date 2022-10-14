package com.camp.app.note.mapper;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.note.service.NoteVO;
@RestController
@CrossOrigin
public interface NoteMapper {
	
	public int getMaxNoteId();
	
//	//내가 작성한 노션 조회
//	public List<NoteVO> getMyNoteList(NoteVO nvo);
//	
//	
//	
//	//초대받은 노션 조회
//	public List<NoteVO> getInvitedList(NoteVO nvo);

	
	//노트 게시글 저장
	public int writeCotents(NoteVO nvo);
	
	
}
