
package com.camp.app.note.mapper;

import com.camp.app.note.service.NoteImgVO;

public interface NoteImgMapper {
	//max Note img 가져오기
	public int getMaxNoteId();
			
	//이미지 저장
	public int insertNoteImg(NoteImgVO imgVO);
	
	
}
