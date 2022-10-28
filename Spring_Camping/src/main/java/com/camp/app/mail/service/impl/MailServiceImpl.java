package com.camp.app.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.mail.mapper.MailMapper;
import com.camp.app.mail.service.MailService;
import com.camp.app.mail.service.MailVO;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	MailMapper mapper;
	
	@Override
	public boolean sendMsg(MailVO mail) {
		mapper.sendMsg(mail);
		return true;
	}
	
	//받은쪽지
	@Override
	public List<MailVO> receivedMsg(String email) {
		return mapper.receivedMsg(email);
	}
	
	//보낸쪽지
	@Override
	public List<MailVO> sendedMsg(String email) {
		return mapper.sendedMsg(email);
	}

	@Override
	public MailVO detailMsg(int mailId) {
		return mapper.detailMsg(mailId);
	}
	
	//읽음확인
	@Override
	public void updateMail(MailVO mail) {
		mapper.updateMail(mail);
	}

	@Override
	public void deleteMail(MailVO mail) {
		mapper.deleteMail(mail);
	}

}
