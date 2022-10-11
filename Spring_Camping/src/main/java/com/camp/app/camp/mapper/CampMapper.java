package com.camp.app.camp.mapper;

import java.util.List;

import com.camp.app.camp.service.CampVO;

public interface CampMapper {
	public List<CampVO> findAll();
	public int count();
	public CampVO findByCampId(int campId);
	public int insert(CampVO camp);
	public int update(CampVO camp);
	
}
