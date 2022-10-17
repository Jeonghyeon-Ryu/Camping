package com.camp.app.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.report.mapper.ReportMapper;
import com.camp.app.report.service.ReportService;
import com.camp.app.report.service.ReportVO;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	ReportMapper mapper;
	
	@Override
	public List<ReportVO> showAll() {
		return mapper.findAll();
	}

	@Override
	public List<ReportVO> showByBoardDivision(int boardDivision) {
		return mapper.findByBoardDivision(boardDivision);
	}

	@Override
	public boolean addReport(ReportVO report) {
		report.setReportId(mapper.findMaxByReportId()+1);
		return mapper.insert(report);
	}

	@Override
	public boolean modifyReport(ReportVO report) {
		return mapper.update(report);
	}

}
