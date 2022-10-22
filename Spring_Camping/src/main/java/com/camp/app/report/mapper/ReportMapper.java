package com.camp.app.report.mapper;

import java.util.List;

import com.camp.app.report.service.ReportVO;

public interface ReportMapper {
	public List<ReportVO> findAll();
	public List<ReportVO> findByEmail(String email);
	public List<ReportVO> findByBoardDivision(int boardDivision);
	public boolean insert(ReportVO report);
	public boolean update(ReportVO report);
	public int findMaxByReportId();
}
