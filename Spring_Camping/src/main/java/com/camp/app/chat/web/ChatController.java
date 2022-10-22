package com.camp.app.chat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.camp.app.chat.service.ChatVO;

@Controller
@CrossOrigin(originPatterns = "*")
public class ChatController {
	
	@Autowired
	SimpMessageSendingOperations sendTemplate;

	@MessageMapping("/getSubscribeInfo")
	public void getSubscribeInfo(String RoomNo) {
		sendTemplate.convertAndSend("/topic/room/" + RoomNo, RoomNo);
	}
	
	@MessageMapping("/send")
	public void send(ChatVO chat) {
//		NoticeVO noticeVO = new NoticeVO();
//		ResNoticeVO resNotice = new ResNoticeVO();
//		ChatListContentResVO res = new ChatListContentResVO();
		
		// N:1 메세지
		sendTemplate.convertAndSend("/topic/room/" + chat.getRoomId(), chat);
	
		for (int i = 0; i < chat.getTargetEmail().size(); i++) {
			System.out.println("=======================");
			System.out.println(chat);
			System.out.println("=======================");
			sendTemplate.convertAndSend("/queue/"+chat.getTargetEmail().get(i),chat);
			//상대방이 같은방에 없을때는 알림을 보낸다.
//			if (cService.getCheckIn(content.getRoomNo(), content.getMemberIds().get(i)) == 0) {
//				res.setContent(content.getContent());
//				res.setRoomNo(content.getRoomNo());
//				res.setMsgTime(content.getHour());
//				System.out.println(res);
//				sendTemplate.convertAndSend("/queue/" + content.getMemberIds().get(i)+ "/notice", content);
//				resNotice.setProfileImge(mService.getMember(content.getMemberId()).getProfileImg());
//				resNotice.setNickname(mService.getMember(content.getMemberId()).getNickName());
//				noticeVO.setMemberId(content.getMemberIds().get(i));
//				noticeVO.setAvatar("require(`@/assets/image/user/" + resNotice.getProfileImge() + "`)");
//				noticeVO.setTitle(resNotice.getNickname());
//				noticeVO.setNoticeId(4);
//				nService.insertNotice(noticeVO);
//			}
//			else {
//	
//			}
		}
	}
}
