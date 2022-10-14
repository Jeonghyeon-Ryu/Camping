package com.camp.app.note.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;

@RestController
@CrossOrigin
public class NoteController {
	
	@Autowired
	NoteService service;
	
	@PostMapping("/WriteNote")
	public void writeNote(@RequestBody NoteVO nvo) { 
		service.writeContents(nvo);
		System.out.println(nvo);
	}
}
