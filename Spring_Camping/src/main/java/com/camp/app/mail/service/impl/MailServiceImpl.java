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

	@Override
	public List<MailVO> receivedMsg(MailVO mail) {
		return mapper.receivedMsg(mail);
	}

	@Override
	public List<MailVO> sendedMsg(int page) {
		return mapper.sendedMsg(page);
	}

	@Override
	public MailVO detailMsg(int mailId) {
		return mapper.detailMsg(mailId);
	}

	@Override
	public void updateMail(int mailStatus) {
		mapper.updateMail(mailStatus);
	}

}
