package com.camp.app.mail.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.mail.service.MailService;
import com.camp.app.mail.service.MailVO;

/**
 * 
 * @author 조하영
 * 쪽지 
 *
 */
@CrossOrigin(originPatterns = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
//@CrossOrigin(origins="*")
@RestController
@RequestMapping("/used")
public class MailController {
	
	@Autowired
	MailService service;
	
	//전송
	@PostMapping("/sendMail")
	public boolean sendMsg(@RequestBody MailVO mail) {
		return service.sendMsg(mail);
	}
	
	//받은메시지전체조회
	@GetMapping("/receivedMail")
	public List<MailVO> receivedMsg(@RequestBody MailVO mail){
		return service.receivedMsg(mail);
	}
}
