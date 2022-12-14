package com.camp.app.note.service;

import lombok.Data;

@Data
public class NoteImgVO {
	private int imgId;
	private int noteId;
	private String originName;
	private String storedName;
	private String imgFormat;
	private String imgPath;
	private long imgSize;
}
