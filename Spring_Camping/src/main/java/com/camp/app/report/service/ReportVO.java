package com.camp.app.report.service;

import lombok.Data;

@Data
public class ReportVO {
	private int reportId;
	private int boardId;
	private int boardDivision;
	private String reportContent;
	private int status;
	private String email;
}
