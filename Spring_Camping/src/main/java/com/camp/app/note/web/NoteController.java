
package com.camp.app.note.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.note.service.InvitedMemberVO;
import com.camp.app.note.service.NoteDto;
import com.camp.app.note.service.NoteImgService;
import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;

@RestController
@CrossOrigin
public class NoteController {
	
	@Autowired
	NoteService service;
	@Autowired
	NoteImgService imgService;
	
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
	//user초대하기
	@PutMapping("/inviteUser")
	public boolean inviteUser(@RequestBody InvitedMemberVO ivo) {
		System.out.println("여길봐여기를");
		System.out.println(ivo);
		return service.inviteUser(ivo);
	}
	//초대받은 list보기
	@GetMapping("/invitedList/{email}")
	public List<NoteVO> showInvitedNoteList(@PathVariable("email") String email) { 
		return service.showInvitedNoteList(email);
	}
	//공유중인 유저정보 가져오기
	@GetMapping("/BlockUser/{noteId}")
	public String showInvitedMember(@PathVariable("noteId") int noteId) { 
		return service.showInvitedMember(noteId);
	}
	//공유끊기
	public void delInvitedMember(@PathVariable("noteId") int noteId) { 
		//System.out.print(nodeDto.getNoteIds().get(0));
		service.delMember(noteId);
	}
	//imgDB에 저장
	@PostMapping("/WriteNoteInfo")
	public boolean saveImg(NoteVO nvo, List<MultipartFile> files) throws IOException { 
		return service.writeContents(nvo, files);
	}
//	//img 정보조회
//	@GetMapping("/GoMyNote/{noteId}")
//	public List<NoteImgVO> findNoteImg(@PathVariable int noteId){ 
//		return imgService.findImg(noteId);
//	}
//	
//	//img 불러오기
//	@GetMapping("/GoMyNote/{imgPath}/{storedName}")
//	public ResponseEntity<Resource> showImage(@PathVariable String imgPath, @PathVariable String storedName){
//		return imgService.showImg(imgPath, storedName);
//	}
}

