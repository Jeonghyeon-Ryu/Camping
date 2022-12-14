package com.camp.app.mail.service;

import java.util.List;

public interface MailService {
	
	//메시지 전송
	public boolean sendMsg(MailVO mail);
	
	//내가받은쪽지조회
	public List<MailVO> receivedMsg(String email);
	
	//내가보낸쪽지조회
	public List<MailVO> sendedMsg(String email);
	
	//쪽지상세보기
	public MailVO detailMsg(int mailId);
	
	//쪽지읽음처리
	public void updateMail(MailVO mail);
	
	//쪽지삭제
	public boolean deleteMail(MailVO mail);
}
