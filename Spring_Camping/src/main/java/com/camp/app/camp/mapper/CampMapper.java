package com.camp.app.camp.mapper;

import java.util.List;

import com.camp.app.camp.service.CampImageVO;
import com.camp.app.camp.service.CampVO;
import com.camp.app.camp.service.InputCampVO;

public interface CampMapper {
	public List<CampVO> findAll();
	public List<CampVO> findByPage(int page);
	public int count();
	public int findMaxByCampId();
	public int findMaxByCampImageId();
	public CampVO findByCampId(int campId);
	public List<CampImageVO> findByCampIdToCampImage(int campId);
	public int insertCamp(CampVO camp);
	public int insertCampImage(CampImageVO campImage);
//	public int update(CampVO camp); 
	
}
