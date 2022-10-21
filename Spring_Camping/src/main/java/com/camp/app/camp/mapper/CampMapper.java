package com.camp.app.camp.mapper;

import java.util.List;

import com.camp.app.camp.service.CampImageVO;
import com.camp.app.camp.service.CampModifyVO;
import com.camp.app.camp.service.CampVO;

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
	public int update(CampVO camp); 
	
	
//	캠핑 수정
	public int insertCampModify(CampModifyVO camp);
	public int insertCampModifyImage(CampImageVO campImage);
	public int findMaxByCampModifyImageId();
	public int findMaxByCampModifyId();
	public int countCampModifyByCampId(int campId);
	
//	저장 캠핑 보기
	public List<CampVO> findByEmail(String email);
}
