package com.camp.app.camp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camp.app.camp.service.CampService;
import com.camp.app.camp.service.CampVO;
import com.camp.app.camp.service.InputCampVO;

@RestController
@CrossOrigin(originPatterns = "http://localhost:8088", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
public class CampController {

	@Autowired
	CampService service;
	
	@PostMapping("/camp")
	public boolean addCamp(InputCampVO camp) {
		System.out.println(camp);
		return service.addCamp(camp);
	}

	@GetMapping("/camp/{page}")
	public List<CampVO> getCampList(@PathVariable("page") int page){
		System.out.println(page);
		return service.showCampByPage(page);
	}
	
}
