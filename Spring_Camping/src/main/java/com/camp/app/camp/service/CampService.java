package com.camp.app.camp.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CampService {
	public List<CampVO> showCampAll();
	public boolean addCamp(CampVO camp);
}
