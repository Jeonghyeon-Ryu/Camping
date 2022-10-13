package com.camp.app.note.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;

public class NoteController {
	
	@Autowired
	NoteService servcie;
	
	@PostMapping("/writeNote")
	public void writeNote(@RequestBody NoteVO nvo) { 
		
	}
}
