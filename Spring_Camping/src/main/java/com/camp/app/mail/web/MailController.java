package com.camp.app.mail.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/java/mail")
public class MailController {
	
	@Autowired
	MailService service;
	
	//전송
	@PostMapping("/sendMail")
	public boolean sendMsg(@RequestBody MailVO mail) {
		return service.sendMsg(mail);
	}
	
	//받은메시지전체조회
	@GetMapping("/receivedMail/{email}")
	public List<MailVO> receivedMsg(@PathVariable String email){
		return service.receivedMsg(email);
	}
	
	//보낸메시지전체조회
	@GetMapping("/sendedMail/{email}")
	public List<MailVO> sendedMsg(@PathVariable String email){
		return service.sendedMsg(email);
	}
	
	//읽음확인
	@PutMapping("/updateMail")
	public void updateMail(@RequestBody MailVO mail) {
		service.updateMail(mail);
	}
}
