package com.camp.app.used.service;

public class ChatVO {
	//채팅(chat)
	private int chatId; //채팅방ID
	private int senderId; //전송인회원번호
	private String chatDate; //생성일
	private int chatType; //게시글 분류
	private int postId; //게시글ID
	private String chatMessage; //내용 -- ?
	private String email; //이메일
	
	//채팅참여(chat_p)
	private int memberId; //채팅방참여회원번호
	private int chatId2; //채팅방Id --?
	
}
