package com.camp.app.note.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.note.service.NoteDto;
import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;

@RestController
@CrossOrigin
public class NoteController {
	
	@Autowired
	NoteService service;
	
	//작성한 노트 저장
	@PostMapping("/WriteNote")
	public boolean writeNote(@RequestBody NoteVO nvo) { 
		int result = service.writeContents(nvo);
		if(result > 0) { 
			return true;
		}else {
			return false;
		}
	};
	//MynoteList조회
	@GetMapping("/MyNoteList/{email}")
	public List<NoteVO> showMyNoteList(@PathVariable("email") String email) { 
		return service.getMyNoteList(email);
	}
	
	//Note단건삭제
	@DeleteMapping("/MyNoteList")
	public void deleteNote(@RequestBody NoteDto nodeDto) { 
		//System.out.print(nodeDto.getNoteIds().get(0));
		service.deleteNote(nodeDto);
	}
	//노트 선택시 노트내용 가져오기
	@GetMapping("/GoMyNote/{noteId}")
	public NoteVO goMyNote(@PathVariable("noteId") int noteId) {
		
		return service.getMyNote(noteId);
	}
	//imgDB에 저장
	@PostMapping("/WriteNote/img")
	public boolean saveImg(@RequestBody List<MultipartFile> files) { 
		System.out.println(files);
		return service.insertImg(files);
	}
}
