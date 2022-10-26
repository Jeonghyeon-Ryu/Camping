package com.camp.app.mail.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class mailVO {
	private int mailId;
	private String mailSender;
	private String mailReceiver;
	private String mailContent;
	private int mailStatus;
	private int usedId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date mailDate;
}
