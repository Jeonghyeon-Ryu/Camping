package com.camp.app.recru.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.app.camp.service.CampVO;
import com.camp.app.deposit.mapper.DepositMapper;
import com.camp.app.deposit.service.DepositVO;
import com.camp.app.member.mapper.MemberMapper;
import com.camp.app.member.service.MemberVO;
import com.camp.app.recru.mapper.RecruMapper;
import com.camp.app.recru.service.RecruService;
import com.camp.app.recru.service.RecruVO;
import com.camp.app.recruEntry.mapper.EntryMapper;
import com.camp.app.recruEntry.service.EntryVO;
import com.camp.app.save.mapper.SaveMapper;
import com.camp.app.save.service.SaveVO;

@Service
public class RecruServiceImpl implements RecruService {

	@Autowired
	RecruMapper mapper;
	@Autowired
	EntryMapper entryMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	DepositMapper depMapper;
	@Autowired
	SaveMapper saveMapper;
	
	//게시글 입력
	@Override
	public int insertRecru(RecruVO recruVO) {
		//게시글 작성자 정보 가져오기
		String email = recruVO.getMemberId();
		MemberVO member = memberMapper.findByEmail(email);
		
		recruVO.setBirth(member.getBirth());
		recruVO.setSex(member.getSex());
		recruVO.setNickname(member.getNickname());
		return mapper.insertRecru(recruVO);
	}
	//전체 조회
	@Override
	public List<RecruVO> recruAllList() {
		Date now = new Date();
		List<RecruVO> list = mapper.findAll();
		for(RecruVO recru : list) {
			if(recru.getRecruStatus()==1 && recru.getGoDate()!=null) {
				int status = now.compareTo(recru.getGoDate()); //오늘날짜와 출발일 비교
				if(status>=0 ) {//공개상태
					recru.setRecruStatus(3);//여행완료
					mapper.updateRecru(recru);//상태 업데이트
				}
			}
		}
		return list;
	}
	//전체조회 - 페이징
	@Override
	public List<RecruVO> findAllPaging(RecruVO recruVO) {
		Date now = new Date();
		List<RecruVO> list = mapper.findAllPaging(recruVO);
		for(RecruVO recru : list) {
			if(recru.getRecruStatus()==1 && recru.getGoDate()!=null) {
				int status = now.compareTo(recru.getGoDate()); //오늘날짜와 출발일 비교
				if(status>=0 ) {//공개상태
					recru.setRecruStatus(3);//여행완료
					mapper.updateRecru(recru);//상태 업데이트
				}
			}
		}
		return list;
	};
	//검색 조회
	@Override
	public List<RecruVO> recruKeywordList(RecruVO recruVO) {
		return mapper.findKeyword(recruVO);
	}
	//최신아이디 조회
	@Override
	public int getMaxRecruId() {
		return mapper.getMaxRecruId();
	}
	//단건조회
	@Override
	public RecruVO findOne(int recruId) {
		//아이디로 게시글 가져오기
		RecruVO info = mapper.selectOne(recruId);
		
		//게시글 작성자 정보 가져오기
		String email = info.getMemberId();
		MemberVO member = memberMapper.findByEmail(email);
		
		info.setBirth(member.getBirth());
		info.setSex(member.getSex());
		info.setNickname(member.getNickname());
		return info;
	}
	//상태변경
	
	@Override
	public int changeStatus(RecruVO recruVO) {
		//게시글 상태변경(1로 변경시 작성자의 정보를 보증금 테이블에 저장)
		int result1 = mapper.changeStatus(recruVO);
		
		if(recruVO.getRecruStatus()!=1) {
			return result1;
		}
		
		//상태가 1로 변경시 (모집중->모집완료) 
		//1. 참가가 수락되지 않은 회원(상태0)의 정보를 거절(2)로 변경
		//2. 참가 수락된 회원(1)의 정보를 보증금 테이블에 저장
			
		//모집글 아이디로 참자가 목록 가져오기
		List<EntryVO> list = entryMapper.recruEnteredList(recruVO.getRecruId());
		for(EntryVO entry: list) {
			if(entry.getEntryStatus()==0) {
				//1. 참가가 수락되지 않은 회원
				EntryVO vo = new EntryVO();
				vo.setEntryStatus(2);
				vo.setEntryId(entry.getEntryId());
				// 정보를 거절로 변경
				entryMapper.updateEntryStatus(vo);
			}else if(entry.getEntryStatus()==1) {
				//1. 참가 수락된 회원
				DepositVO vo = new DepositVO();
				vo.setDepositId(depMapper.getMaxDepositId()+1);
				vo.setRecruId(entry.getRecruId());
				vo.setMemberId(entry.getMemberId());
				//보증금 테이블에 저장
				depMapper.insertDeposit(vo);
			}
		}
		return 1;
	}
	//작성자 정보 (생일, 성별)
	@Override
	public RecruVO getMemberInfo(String email) {
		return mapper.getMemberInfo(email);
	}
	//마이페이지 
	@Override
	public List<RecruVO> myRecru(String memberId) {
		return mapper.myRecru(memberId);
	}
	//저장여부 확인(찜)
	@Override
	public boolean isExist(SaveVO save) {
		return saveMapper.exist(save);
	}
	//수정
	@Override
	public int updateRecru(RecruVO recruVO) {
		return mapper.updateRecru(recruVO);
	}
	//접근 상태 변경
	@Override
	public int changeShowStatus(RecruVO recruVO) {
		return mapper.changeShowStatus(recruVO);
	}
	//완료된 모집의 참가자들 조회(모집자+참가자)
	@Override
	public List<RecruVO> findRecruMembers(int recruId) {
		return mapper.findRecruMembers(recruId);
	}
	//후기등록
	@Override
	public int insertReview(RecruVO recruVO) {
		return mapper.insertReview(recruVO);
	}
	//캠핑장검색
	@Override
	public List<CampVO> searchCamp(String region) {
		System.out.println("초기지역 : "+region);
		List<CampVO> list = mapper.searchCamp(region);
		if(list.size()== 0) {
			String city[] = region.split(" ");
			region= city[0]+" "+city[1];
			System.out.println("지역 : "+region);
			list = mapper.searchCamp(region);
		}
		return list;
	}

}
