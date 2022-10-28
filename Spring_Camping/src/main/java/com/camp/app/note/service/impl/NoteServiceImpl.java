package com.camp.app.note.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.note.mapper.NoteImgMapper;
import com.camp.app.note.mapper.NoteMapper;
import com.camp.app.note.service.InvitedMemberVO;
import com.camp.app.note.service.NoteDto;
import com.camp.app.note.service.NoteImgVO;
import com.camp.app.note.service.NoteService;
import com.camp.app.note.service.NoteVO;

@Service
public class NoteServiceImpl implements NoteService {
	private String imagePath = "/home/upload/note/";
	//집에서 밑에꺼 주석풀기 위에꺼는 주석막기
//	private String imagePath = "d:\\upload\\note\\";
	@Autowired
	NoteMapper mapper;
	@Autowired
	NoteImgMapper imgMapper;

	// Note Insert
	@Override
	public boolean writeContents(NoteVO nvo, List<MultipartFile> files) {

		// imgInfo에 이미지가 들어갈 경우 줄구분이 없다
		// 그래서 페이지 내에 총 파일 개수가 들어가게 된다
		List<NoteImgVO> imgInfo = new ArrayList<>();
		nvo.setNoteId(mapper.getMaxNoteId());
		
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = this.imagePath + directoryPath;
		// 폴더 주소
		
		if (files != null && files.size() != 0) {
			File uploadPathDir = new File(uploadPath);
			if (!uploadPathDir.exists()) {
				uploadPathDir.mkdirs();
			}
			files.forEach(file -> {
				NoteImgVO img = new NoteImgVO();

				img.setOriginName(file.getOriginalFilename());
				img.setImgFormat(img.getOriginName().substring(img.getOriginName().indexOf('.') + 1));
				img.setImgSize(file.getSize());
				img.setImgPath(directoryPath);
				img.setNoteId(nvo.getNoteId());

				// uuid를 통한 랜덤으로 아이디 주기
				UUID uuid = UUID.randomUUID();
				String[] uuids = uuid.toString().split("-");
				img.setStoredName(uuids[0] + "_" + img.getOriginName());
				// 업로드
				File uploadFile = new File(uploadPath, img.getStoredName());

				try {
					file.transferTo(uploadFile);
					imgInfo.add(img);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				imgMapper.insertNoteImg(img);

			});
			//수정할때 추가한 이미지는 기존방식 그대로
			String noteContent = "";
			
			int temp = 0;
			for (int i = 0; i < nvo.getNoteContents().size(); i++) {
				
				// System.out.println(nvo.getNoteContents().get(i).substring(4,
				// nvo.getNoteContents().get(i).length()));

				if (nvo.getNoteContents().get(i).substring(0, 3).equals("IMG")) {// 줄 수

					int imgCnt = Integer.parseInt(nvo.getNoteContents().get(i).substring(4, nvo.getNoteContents().get(i).length()));
					// 줄 이미지 개수

					int tempLimit = imgCnt + temp;
					
					noteContent += "IMG"+i+">";

					for (int j = temp; j < tempLimit; j++) {

						noteContent = noteContent + "imgPath:" + imgInfo.get(j).getImgPath() + ",storedName:"
								+ imgInfo.get(j).getStoredName() + "$";
						temp++;
					}
					noteContent = noteContent + "|||||||||";
				} else {
					noteContent += nvo.getNoteContents().get(i) + "|||||||||";
				}
			};
			
			//기존에 이미지 처리
			nvo.setNoteContent(noteContent);

			mapper.writeCotents(nvo);

		} else { 
			String noteContent = "";

			for (int i = 0; i < nvo.getNoteContents().size(); i++) {
				noteContent += nvo.getNoteContents().get(i) + "|||||||||";
			};
			nvo.setNoteContent(noteContent);
			nvo.setNoteId(mapper.getMaxNoteId());

			mapper.writeCotents(nvo);
		}
		return true;
	}

	// 노트 수정하기
	@Override
	public boolean updateContents(NoteVO nvo, List<MultipartFile> files) {
		// imgInfo에 이미지가 들어갈 경우 줄구분이 없다
		// 그래서 페이지 내에 총 파일 개수가 들어가게 된다
		List<NoteImgVO> imgInfo = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String directoryPath = sdf.format(date);
		String uploadPath = this.imagePath + directoryPath;
		// 폴더 주소
		System.out.println("-----------------------------------files");
		System.out.println(files);

		// 이미지가 없는 경우에는
		String noteContent = "";

		for (int i = 0; i < nvo.getNoteContents().size(); i++) {
			noteContent += nvo.getNoteContents().get(i) + "|||||||||";
		};
		
		nvo.setNoteContent(noteContent);
		//nvo.setNoteId(mapper.getMaxNoteId());

		mapper.updateContents(nvo);

		if (files != null && files.size() != 0) {
			
			
			//imgMapper.deleteImg(nvo.getNoteId());
			
			
			
			File uploadPathDir = new File(uploadPath);

			if (!uploadPathDir.exists()) {
				uploadPathDir.mkdirs();
			}

			files.forEach(file -> {
				NoteImgVO img = new NoteImgVO();

				img.setOriginName(file.getOriginalFilename());
				img.setImgFormat(img.getOriginName().substring(img.getOriginName().indexOf('.') + 1));
				img.setImgSize(file.getSize());
				img.setImgPath(directoryPath);
				img.setNoteId(nvo.getNoteId());

				// uuid를 통한 랜덤으로 아이디 주기
				UUID uuid = UUID.randomUUID();
				String[] uuids = uuid.toString().split("-");
				img.setStoredName(uuids[0] + "_" + img.getOriginName());
				// 업로드
				File uploadFile = new File(uploadPath, img.getStoredName());

				try {
					file.transferTo(uploadFile);
					imgInfo.add(img);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				imgMapper.insertNoteImg(img);

			});
			noteContent = "";
			int temp = 0;
			for (int i = 0; i < nvo.getNoteContents().size(); i++) {
				// 이부분 다 수정해야댐
				// System.out.println(nvo.getNoteContents().get(i).substring(4,
				// nvo.getNoteContents().get(i).length()));

				if (nvo.getNoteContents().get(i).substring(0, 7).equals("imgPath")) {// 줄 수

					int imgCnt = Integer
							.parseInt(nvo.getNoteContents().get(i).substring(nvo.getNoteContents().get(i).length()-1, nvo.getNoteContents().get(i).length()));
					// 줄 이미지 개수
					System.out.println("이미지카운트"+ imgCnt);
					int tempLimit = imgCnt + temp;
					
					//noteContent += "IMG"+i+">";
					noteContent += nvo.getNoteContents().get(i).substring(0,nvo.getNoteContents().get(i).length()-1);
					System.out.println("콘텐츠" + nvo.getNoteContents().get(i).substring(0,nvo.getNoteContents().get(i).length()-1));
					
					for (int j = temp; j < tempLimit; j++) {
						
						noteContent = noteContent + "imgPath:" + imgInfo.get(j).getImgPath() + ",stroedName:"
								+ imgInfo.get(j).getStoredName() + "$";
						temp++;
					}
					noteContent = noteContent + "|||||||||";
				} else {
					noteContent += nvo.getNoteContents().get(i) + "|||||||||";
				}
				
			};
			System.out.println("노트컨텐츠 " + noteContent);
			nvo.setNoteContent(noteContent); 
			// nvo.setNoteContent(nvo.getNoteContent+noteContent)여기 문제생김 더하기

			mapper.updateContents(nvo);
		}
		return true;
	}

	// 내가 작성한 노트
	@Override
	public List<NoteVO> getMyNoteList(String email) {
		List<NoteVO> listInfo = mapper.getMyNoteList(email);
		return listInfo;
	}

	// 노트 삭제
	@Override
	public void deleteNote(NoteDto dto) {
		for (int i = 0; i < dto.getNoteIds().size(); i++) {
			mapper.deleteNote(dto.getNoteIds().get(i));
		}
	}

	// 노트내용 가져오기
	@Override
	public NoteVO getMyNote(int noteId) {

		NoteVO nvo = mapper.getMyNoteInfo(noteId);
		String temp = nvo.getNoteContent();

		List<String> tempList = new ArrayList<>();

		while (temp.indexOf("|||||||||") != -1) {
			tempList.add(temp.substring(0, temp.indexOf("|||||||||")));

			temp = temp.substring(temp.indexOf("|||||||||") + 9, temp.length());
		}
		nvo.setNoteContents(tempList);
		nvo.getNoteContents().forEach(s -> System.out.println("*********" + s));

		return nvo;

	}

	// 초대하기
	@Override
	public boolean inviteUser(InvitedMemberVO ivo) {

		NoteVO nvo = new NoteVO();
		nvo.setNoteId(ivo.getNoteId());

		String invitedMember = "";

		String temp = mapper.getMyNoteInfo(ivo.getNoteId()).getInvitedMember();
		System.out.println("getMyNoteInfo " + temp);
		if (temp == null) {
			temp = "";
		}

		for (int i = 0; i < ivo.getInvitedMember().size(); i++) {

			invitedMember += ivo.getInvitedMember().get(i);
		}
		// invitedMember += ivo.getInvitedMember().get(ivo.getInvitedMember().size()-1);

		nvo.setInvitedMember(temp + invitedMember);
		System.out.println("확인---");
		System.out.println(nvo.getInvitedMember());

		mapper.invitedMember(nvo);

		return true;

	}

	// 공유중인 노션 리스트
	@Override
	public List<NoteVO> showInvitedNoteList(String email) {

		email = "," + email + ",";
		List<NoteVO> InvitedList = mapper.getInvitedNoteList(email);

		return InvitedList;
	}

	// 공유중인 회원 메일 가져오기
	@Override
	public String showInvitedMember(int noteId) {

		String InvitedMember = mapper.getInvitedMember(noteId);

		System.out.println(InvitedMember);
		return InvitedMember;
	}

	@Override
	public void delMember(InvitedMemberVO ivo) {
		NoteVO nvo = new NoteVO();

		nvo.setNoteId(ivo.getNoteId());

		for (int i = 0; i < ivo.getInvitedMember().size(); i++) {
			nvo.setInvitedMember(ivo.getInvitedMember().get(i));
		}
		mapper.delInvitedMember(nvo);

	}

}
