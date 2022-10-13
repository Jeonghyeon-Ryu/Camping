package com.camp.app.note.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoteVO {
	private int noteId;
	private String title;
	private String noteContents;
	private String invitedMember;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writeDate;
	
}
