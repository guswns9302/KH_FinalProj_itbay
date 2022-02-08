package web.com.itbay.members.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String login(String nickname,String pw) {
		MembersDTO inputdata = new MembersDTO(nickname,pw);
		MembersDTO logindata = new MembersDTO();
		logindata = service.login(inputdata);
		
		if(logindata != null) {
			System.out.println("로그인 성공");
			System.out.println("접속한 아이디 : " + logindata.getNickname());
			return "redirect:/";
		}
		else {
			return "/login";
		}	
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
		session.setAttribute("kakaoMember", kakaoLogin_Memberinfo);
				
		if(session.getAttribute("token") != null) {
			session.setAttribute("login", true);
		}
		else {
			session.setAttribute("login", false);
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/kakaologout", method = RequestMethod.GET)
	public String kakaoLogout(HttpSession session) {
		kakaoservice.logout(session.getAttribute("token").toString());
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public String kakaologin(Model model, HttpSession session) {
		model.addAttribute("kakaoMember", session.getAttribute("kakaoMember"));
		
		return "myinfo";
	}
}
