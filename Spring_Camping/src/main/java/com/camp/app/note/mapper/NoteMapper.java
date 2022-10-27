package com.camp.app.note.mapper;



import java.util.List;

import org.springframework.stereotype.Component;

import com.camp.app.note.service.InvitedMemberVO;
import com.camp.app.note.service.NoteImgVO;
import com.camp.app.note.service.NoteVO;
@Component
public interface NoteMapper {
	//노트id 생성
	public int getMaxNoteId();
	
	//노트imgId 생성
	public int getMaxNoteImgId();
	
	//내가 작성한 노션 조회
	public List<NoteVO> getMyNoteList(String email);

//	//초대받은 노션 조회
//	public List<NoteVO> getInvitedList(NoteVO nvo);

	//노트 게시글 저장
	public int writeCotents(NoteVO nvo);
	
	//노트삭제
	public int deleteNote(int noteId);
	
	//노트ID로 노트내용 가져오기
	public NoteVO getMyNoteInfo(int noteId);
	
	//이미지 저장
	public int insertNoteImg(NoteImgVO img);
	
	//노트 수정
	public int updateContents(NoteVO nvo);
	
	//노트ID로 img불러오기
	public List<NoteImgVO> findByNoteIdToImg(int noteId);
	
	//초대하기
	public int invitedMember(NoteVO nvo);
	
	//초대받은 리스트 
	public List<NoteVO> getInvitedNoteList(String email);
	
	//초대한 유저 리스트
	public String getInvitedMember(int noteId);
	
	//공유끊기
	public int delInvitedMember(NoteVO nvo);
	
}
