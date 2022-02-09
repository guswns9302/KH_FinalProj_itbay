package web.com.itbay.members.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import web.com.itbay.img.model.ImgDTO;

@Service
public class KakaoLoginService {

	@Autowired
	MembersDAO dao;
	
	public HashMap<String, String> accessToken(String code) {
		String token_URL = "https://kauth.kakao.com/oauth/token";
		String access_Token = null;
		String refresh_Token = null;
		
		HashMap<String , String > token = new HashMap<String, String>();
		
		try {
			URL url = new URL(token_URL);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			
			// post 요청을 위해서 setDoOutput의 기본값 false를 true로 변경
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("grant_type=authorization_code");
			stringBuilder.append("&client_id=ea71971929c14c8aef7a4bef54e9b082");
			stringBuilder.append("&redirect_uri=http://localhost/kakao");
			stringBuilder.append("&code=" + code);
			
			bufferedWriter.write(stringBuilder.toString());
			bufferedWriter.flush();
			
			// 응답 확인 : 200이면 성공
//			int responseCode = urlConnection.getResponseCode();
//			System.out.println("responseCode : " + responseCode);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String result = bufferedReader.readLine();
			
			// json data 확인
//			System.out.println("response body : " + result);
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			access_Token = jsonObject.get("access_token").toString();
			refresh_Token = jsonObject.get("refresh_token").toString();
			
			token.put("access_token", access_Token);
			token.put("refresh_token", refresh_Token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return token;
	}

	public HashMap<String, String> kakaoMemberInfo(String access_Token) {
		String kakaoMember_info_url = "https://kapi.kakao.com/v2/user/me";
		HashMap<String, String> kakaoMember = new HashMap<String, String>();
		try {
			URL url = new URL(kakaoMember_info_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Authorization", "Bearer " + access_Token);
			
			// 응답 확인 : 200이면 성공
//			int responseCode = httpURLConnection.getResponseCode();
//			System.out.println("responseCode : " + responseCode);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String result = bufferedReader.readLine();
			
			// json data 확인
			System.out.println("response body : " + result);
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject member_properties = (JSONObject) jsonObject.get("properties");
			JSONObject member_kakao_account = (JSONObject) jsonObject.get("kakao_account");
			
			String kakaoNickName = member_properties.get("nickname").toString();
			String profile_image = member_properties.get("profile_image").toString();
			String email = member_kakao_account.get("email").toString();
			String gender = member_kakao_account.get("gender").toString();
			kakaoMember.put("kakaoNickName", kakaoNickName);
			kakaoMember.put("profile_image", profile_image);
			kakaoMember.put("email", email);
			kakaoMember.put("gender", gender);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kakaoMember;
	}

	public void dataInsert(MembersDTO logindata) {
		int result = dao.insertKakaoLoginData(logindata);
		
		if(result == 1) {
			System.out.println("데이터 입력 성공");
		}
		else {
			System.out.println("데이터 입력 실패");
		}
	}

	public boolean duplicateLoginData(String nickname) {
		MembersDTO dto = dao.duplicateLoginData(nickname);
		if(dto == null) {
			// 중복된 아이디가 있음
			return false;
		}
		else {
			// 중복된 아이디가 없음
			return true;
		}
	}

	public void dataInsert_img(MembersDTO logindata) {
		ImgDTO imgdto = new ImgDTO();
		imgdto.setMembers_id(logindata.getId());
		imgdto.setImg_name(logindata.getImg_name());
		
		int result = dao.insertKakaoProfileImg(imgdto);
		if(result == 1) {
			System.out.println("카카오 프로필 데이터 입력 성공");
		}
		else {
			System.out.println("카카오 프로필 데이터 입력 실패");
		}
	}

	public MembersDTO getLoginData(MembersDTO kakaoLogindata) {
		MembersDTO kakaoLogin = dao.selectKakaoLogin(kakaoLogindata);
		return kakaoLogin;
	}

//	public void logout(String access_Token) {
//		String kakao_logout_url = "https://kapi.kakao.com/v1/user/logout";
//		
//		try {
//			URL url = new URL(kakao_logout_url);
//			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//			
//			httpURLConnection.setRequestMethod("POST");
//			httpURLConnection.setRequestProperty("Authorization", "Bearer " + access_Token);
//			
//			// 응답 확인 : 200이면 성공
////			int responseCode = httpURLConnection.getResponseCode();
////			System.out.println("responseCode : " + responseCode);
//			
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//			String result = bufferedReader.readLine();
//			
//			System.out.println("logout 요청 : " + result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
