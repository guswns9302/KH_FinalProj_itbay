package web.com.itbay.members.controller;


import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import web.com.itbay.members.model.KakaoLoginService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MembersService;

@Controller
public class MembersController {

	@Autowired
	MembersService service;
	
	@Autowired
	KakaoLoginService kakaoservice;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		return "/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String nickname,String pw, HttpSession session) {
		MembersDTO inputdata = new MembersDTO(nickname,pw);
		MembersDTO logindata = new MembersDTO();
		logindata = service.login(inputdata);
		
		if(logindata != null) {
			System.out.println("로그인 성공");
			System.out.println("접속한 아이디 : " + logindata.getNickname());
			session.setAttribute("login", true);
			session.setAttribute("loginMember", logindata);
			return "redirect:/";
		}
		else {
			session.setAttribute("login", false);
			return "/login";
		}	
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/kakao", method = RequestMethod.GET)
	public String kakaologin(String code, Model model, HttpSession session) {
		HashMap<String , String> token = kakaoservice.accessToken(code);
		HashMap<String , String> kakaoLogin_Memberinfo = kakaoservice.kakaoMemberInfo(token.get("access_token"));
		System.out.println("login for kakao");
		System.out.println("kakao nickname = " + kakaoLogin_Memberinfo.get("kakaoNickName"));
		System.out.println("kakao profile_image = " + kakaoLogin_Memberinfo.get("profile_image"));
		System.out.println("kakao email = " + kakaoLogin_Memberinfo.get("email"));
		System.out.println("kakao gender = " + kakaoLogin_Memberinfo.get("gender"));
		
		session.setAttribute("token", token.get("access_token"));
		
		MembersDTO kakaoLogindata = new MembersDTO();
		kakaoLogindata.setNickname(kakaoLogin_Memberinfo.get("email"));
		kakaoLogindata.setUsername(kakaoLogin_Memberinfo.get("kakaoNickName"));
		kakaoLogindata.setEmail_address(kakaoLogin_Memberinfo.get("email"));
		kakaoLogindata.setImg_name(kakaoLogin_Memberinfo.get("profile_image"));
		
		if(session.getAttribute("token") != null) {
			session.setAttribute("login", true);
			
			if(kakaoservice.duplicateLoginData(kakaoLogindata.getNickname())) {
				System.out.println("이미 데이터가 존재합니다.");
			}
			else {
				System.out.println("없는 데이터 입니다.");
				kakaoservice.dataInsert(kakaoLogindata);
				kakaoservice.dataInsert_img(kakaoLogindata);
			}
			MembersDTO logindata = kakaoservice.getLoginData(kakaoLogindata);
			session.setAttribute("loginMember", logindata);
		}
		else {
			session.setAttribute("login", false);
		}
		
		return "redirect:/";
	}
	
//	@RequestMapping(value = "/kakaologout", method = RequestMethod.GET)
//	public String kakaoLogout(HttpSession session) {
//		kakaoservice.logout(session.getAttribute("token").toString());
//		session.invalidate();
//		return "redirect:/";
//	}
	
	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public String kakaologin(Model model, HttpSession session) {
		
			MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
			model.addAttribute("loginData",loginData);
		
		return "myinfo";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(String nickname, String pw, String email_address, String username, Date birth, String phone, String address) {
		
		MembersDTO membersjoin = new MembersDTO();
		membersjoin.setNickname(nickname);
		membersjoin.setPw(pw);
		membersjoin.setUsername(username);
		membersjoin.setBirth(null);
		membersjoin.setPhone(phone);
		membersjoin.setAddress(address);
		
		MembersService joinservice = new MembersService();
		boolean result = joinservice.join(membersjoin);
		
		if(result == true) {
			System.out.println("회원가입 성공");
		}
		else
		{
			System.out.println("회원가입 실패");
		}
		return "redirect:/";
	}
}
