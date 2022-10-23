package com.camp.app.note.service;

import java.util.List;

import lombok.Data;

@Data
public class InvitedMemberVO {
	private List<String> invitedMember;
	private int noteId;
}
