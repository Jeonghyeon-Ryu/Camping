package com.camp.app.camp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.camp.mapper.CampMapper;
import com.camp.app.camp.service.CampService;
import com.camp.app.camp.service.CampVO;

@Service
public class CampServiceImpl implements CampService{

	@Autowired
	CampMapper mapper;
	
	@Override
	public List<CampVO> showCampAll() {
		List<CampVO> campList = mapper.findAll();
		return null;
	}
	
	@Override
	public boolean addCamp(CampVO camp) {
		int result = mapper.insert(camp);
		if(result>0) {
			return true;
		} else {
			return false;
		}
	}


}
