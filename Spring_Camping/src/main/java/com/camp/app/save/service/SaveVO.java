package com.camp.app.save.service;

import lombok.Data;

@Data
public class SaveVO {
	private int saveId;
	private int boardId;
	private int boardDivision;
	private String email;
}
