
package com.camp.app.note.mapper;

import java.util.List;

import com.camp.app.note.service.NoteImgVO;

public interface NoteImgMapper {
	//max NoteId 가져오기
	public int getMaxNoteId();
	
	//max imgId
	public int getMaxNoteImgId();
			
	//이미지 저장
	public int insertNoteImg(NoteImgVO imgVO);
	
	//노트별 이미지 info 가져오기
	public List<NoteImgVO> findNoteImgInfo(int noteId);
	
	//노트 이미지 수정하기전 데이터 삭제
	public int deleteImg(int noteId);
	
	
}
