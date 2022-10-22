package com.camp.app.report.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ReportService {
	public List<ReportVO> showAll();
	public List<ReportVO> showReportByUser(String email);
	public List<ReportVO> showByBoardDivision(int boardDivision);
	public boolean addReport(ReportVO report);
	public boolean modifyReport(ReportVO report);
}
