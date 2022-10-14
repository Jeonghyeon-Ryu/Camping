package com.camp.app.used.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.used.mapper.UsedMapper;
import com.camp.app.used.service.UsedService;
import com.camp.app.used.service.UsedVO;

@Service
public class UsedServiceImpl implements UsedService {
	
	@Autowired
	UsedMapper mapper;

	//지워도될듯???
	@Override
	public int getUsedId() {
		return mapper.getUsedId();
	}

	//등록
	@Override
	public int insertUsed(UsedVO usedVO) {
		 return mapper.insertUsed(usedVO);
	}
	
	//전체조회
	@Override
	public List<UsedVO> selectAllUsedList() {
		return mapper.selectAllUsedList();
	}

	//단건조회
	@Override
	public UsedVO findDetail(int usedId) {
		return mapper.findDetail(usedId);
	}
	
	//수정
	@Override
	public void updateUsed(UsedVO usedVO) {
		mapper.updateUsed(usedVO);
	}

	//삭제
	@Override
	public int deleteUsed() {
		// TODO Auto-generated method stub
		return 0;
	}

	//필터검색
	@Override
	public List<UsedVO> searchUsedList() {
		// TODO Auto-generated method stub
		return null;
	}

	//키워드검색
	@Override
	public int findUsedKeyword(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//거래상태변경
	@Override
	public int updateDeal() {
		// TODO Auto-generated method stub
		return 0;
	}

	//게시글상태변경
	@Override
	public int updateRestrict() {
		// TODO Auto-generated method stub
		return 0;
	}

	//조회수증가
	@Override
	public int updateCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	//좋아요증가
	@Override
	public int updateLike() {
		// TODO Auto-generated method stub
		return 0;
	}



}
