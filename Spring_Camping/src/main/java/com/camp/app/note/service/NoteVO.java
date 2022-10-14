package com.camp.app.note.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoteVO {
	private int noteId;
	private String title;
	private List<String> noteContents;
	private String noteContent;
	private String invitedMember;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writeDate;
}
