package com.camp.app.report.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.report.service.ReportService;
import com.camp.app.report.service.ReportVO;

@RestController
@CrossOrigin
public class ReportController {
	
	@Autowired
	ReportService service;
	
	@GetMapping("/report")
	public List<ReportVO> showAll(){
		return service.showAll();
	}
	
	@PostMapping("/report")
	public boolean addReport(@RequestBody ReportVO report) {
		return service.addReport(report);
	}
}
