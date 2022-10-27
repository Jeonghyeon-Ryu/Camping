package com.camp.app.note.service;

import java.util.List;

import lombok.Data;

@Data
public class NoteVO {
	private int noteId;
	private String title;
	private String noteContent;
	private String invitedMember;
	private String email;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String writeDate;
	private String noteStatus;
	private List<String> noteContents;
}
