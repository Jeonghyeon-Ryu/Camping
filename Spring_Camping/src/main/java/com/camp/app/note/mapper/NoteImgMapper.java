
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
	
	//노트별 이미지
	public List<NoteImgVO> findNoteImg(int noteId);
	
	
}
