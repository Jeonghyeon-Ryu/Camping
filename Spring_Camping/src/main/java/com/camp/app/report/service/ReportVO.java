package com.camp.app.report.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportVO {
	private int reportId;
	private int boardId;
	private int boardDivision;
	private String reportDivision;
	private String reportContent;
	private int status;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;
}
