package com.camp.app.member.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.camp.app.member.service.AuthVO;
import com.camp.app.member.service.MemberService;
import com.camp.app.member.service.MemberVO;
import com.camp.app.member.service.ProfileImageVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/java")
public class MemberController {

	@Autowired
	MemberService service;

	@GetMapping("/email")
	public boolean isEmail(@RequestParam String email) {
		return !service.checkEmail(email);
	}

	@GetMapping("/nickname")
	public boolean isNickname(@RequestParam String nickname) {
		return !service.checkNickname(nickname);
	}

	@GetMapping("/auth")
	public AuthVO isAuth(@RequestParam String impUid) throws IOException, IamportResponseException {
		IamportClient client = new IamportClient("4717174862060476",
				"ycuRhOuX16mxrzq72ZYGxvEhg1PmAsLKyYPnviIlq0nTy7ePYB9cZRaJik9Pff1axfPjvw5h4Idn3xCI");
		IamportResponse<Certification> certificationResponse = client.certificationByImpUid(impUid);

		AuthVO auth = new AuthVO();
		auth.setName(certificationResponse.getResponse().getName());
		auth.setPhoneNumber(certificationResponse.getResponse().getPhone());
		auth.setBirth(certificationResponse.getResponse().getBirth());

		return auth;
	}

	@GetMapping("/member")
	public List<MemberVO> users() {
		return service.showAllMember();
	}

	@GetMapping("/member/{email}")
	public MemberVO user(@PathVariable String email) {
		return service.findByEmail(email);
	}

	@GetMapping("/emailByNickname/{nickname}")
	public MemberVO emailByNickname(@PathVariable("nickname") String nickname) {
		System.out.println(nickname);
		return service.findByNickname(nickname);
	}

	@GetMapping("/member/{email}/{password}")
	public boolean verifyMember(@PathVariable String email, @PathVariable String password) {
		MemberVO member = new MemberVO();
		member.setEmail(email);
		member.setPassword(password);
		System.out.println(member);
		if (password.equals(service.findByEmail(email).getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	@PostMapping("/member")
	public boolean signup(@RequestBody MemberVO member) {
		System.out.println(member);
		int result = service.signup(member);
		System.out.println("Result : " + result);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@PutMapping("/member")
	public boolean modifyPassword(@RequestBody MemberVO member) {
		if (service.modifyPassword(member) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("/profile/{email}")
	public ProfileImageVO profileImage(@PathVariable String email) {
		return service.showProfileImage(email);
	}

	@GetMapping("/profile/{imagePath}/{storedName}")
	public ResponseEntity<Resource> showImage(@PathVariable String imagePath, @PathVariable String storedName) {
		return service.showImage(imagePath, storedName);
	}

	@PostMapping("/member/modify")
	public boolean modifyMember(MemberVO member, MultipartFile file) {
		int result = service.modifyMember(member, file);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@PostMapping("/login")
	public MemberVO login(@RequestBody MemberVO member, HttpServletRequest request) {
		MemberVO result = service.login(member);
		System.out.println("email " + result.getEmail());
		if (result != null) {
			request.getSession(true).setAttribute("email", result.getEmail());
			System.out.println(request.getSession().getAttribute("email"));
		}
		return result;
	}

	@PostMapping("/logout")
	public boolean logout(SessionStatus status) {
		if (!status.isComplete()) {
			status.setComplete();
		}
		return true;
	}

	@GetMapping("/login")
	public MemberVO kakaoLogin(String code) {
//		POST /oauth/token HTTP/1.1
//		Host: kauth.kakao.com
//		Content-type: application/x-www-form-urlencoded;charset=utf-8
		System.out.println("code : " + code);
		HttpURLConnection conn = null;

		try {
			URL url = new URL("https://kauth.kakao.com/oauth/token");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=b0f34304cdd8f262ee81a86644e1a33e"); // 본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:8081/Login"); // 본인이 설정해 놓은 경로
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();
			
//		    결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			String access_Token = element.getAsJsonObject().get("access_token").getAsString();
			String refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
			
			br.close();
			bw.close();
			
			url = new URL("https://kapi.kakao.com/v2/user/me");
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);
			bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			bw.flush();

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String inputLine = "";
			sb = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			System.out.println("sb : " + sb);
			// 무식하게 가져오는 방법 | JSONParsor 이용하면 쉬움 
			String temp = sb.toString().substring(sb.indexOf("\"email\":") + 9, sb.length());
			String kakaoId = temp.substring(0, temp.indexOf("\",\""));
//			String profileImgUrl = sb.toString().substring(sb.indexOf("\"profile_image_url\":") + 21,
//					sb.toString().indexOf("\"is_default_image\":") - 2); 
			// Kakao유저 정보 vs DB유저 정보 비교 
			// DB 이전 kakao 로그인 기록 있으면 : 로그인 성공 -> Session저장 -> 메인화면
			// DB 이전 kakao 로그인 기록 없으면 : 카카오 연동페이지 -> 회원가입 OR 로그인 -> 메인화면
			MemberVO member = service.findBySocialEmail(kakaoId);
			
			if(member == null) {
				member = new MemberVO();
				member.setSocialEmail(kakaoId);
			}
			
			br.close();
			bw.close();
			
			return member;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
