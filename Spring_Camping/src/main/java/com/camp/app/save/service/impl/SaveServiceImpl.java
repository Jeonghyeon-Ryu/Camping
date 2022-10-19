package com.camp.app.save.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.save.mapper.SaveMapper;
import com.camp.app.save.service.SaveService;
import com.camp.app.save.service.SaveVO;

@Service
public class SaveServiceImpl implements SaveService {

	@Autowired
	SaveMapper mapper;
	
	@Override
	public List<SaveVO> showAll() {
		return mapper.findAll();
	}

	@Override
	public boolean isExist(SaveVO save) {
		if(mapper.exist(save)>0) return true;
		else return false;
	}

	@Override
	public boolean save(SaveVO save) {
		save.setSaveId(mapper.findMaxSaveId()+1);
		return mapper.insert(save);
	}

	@Override
	public boolean remove(SaveVO save) {
		return mapper.delete(save);
	}

}
