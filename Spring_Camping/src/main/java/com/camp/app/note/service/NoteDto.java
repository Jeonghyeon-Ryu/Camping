package com.camp.app.note.service;
import java.util.List;

import lombok.Data;

@Data
//통신하기위해 DataTransferObject를 따로
//Dto는 통신전용 object
public class NoteDto{
	private List<Integer> noteIds;
}
