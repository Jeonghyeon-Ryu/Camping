package com.camp.app.mail.service;

import java.util.List;

public interface MailService {
	
	//메시지 전송
	public boolean sendMsg(MailVO mail);
	
	//내가받은쪽지조회
	public List<MailVO> receivedMsg(MailVO mail);
	
	//내가보낸쪽지조회
	public List<MailVO> sendedMsg(int page);
	
	//쪽지상세보기
	public MailVO detailMsg(int mailId);
	
	//쪽지읽음처리
	public void updateMail(int mailStatus);
}
