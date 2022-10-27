package com.camp.app.mail.mapper;

import java.util.List;

import com.camp.app.mail.service.MailVO;

public interface MailMapper {
	//메시지 전송
	public int sendMsg(MailVO mail);
	
	//내가받은쪽지조회
	public List<MailVO> receivedMsg(String email);
	
	//내가보낸쪽지조회
	public List<MailVO> sendedMsg(String email);
	
	//쪽지상세보기
	public MailVO detailMsg(int mailId);
	
	//쪽지읽음처리
	public void updateMail(MailVO mail);
	
}
