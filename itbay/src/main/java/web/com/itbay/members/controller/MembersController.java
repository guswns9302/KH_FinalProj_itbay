package web.com.itbay.members.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import web.com.itbay.img.model.ImgDAO;
import web.com.itbay.img.model.ImgDTO;
import web.com.itbay.img.model.ImgService;
import web.com.itbay.members.model.KakaoLoginService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MembersService;

@Controller
public class MembersController {

	@Autowired
	MembersService service;
	
	@Autowired
	KakaoLoginService kakaoservice;
	
	@Autowired
	ImgService imgservice;
	
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
		
		session.setAttribute("token", token.get("access_token"));
		
		MembersDTO kakaoLogindata = new MembersDTO();
		kakaoLogindata.setNickname(kakaoLogin_Memberinfo.get("email"));
		kakaoLogindata.setUsername(kakaoLogin_Memberinfo.get("kakaoNickName"));
		kakaoLogindata.setEmail_address(kakaoLogin_Memberinfo.get("email"));
		
		if(session.getAttribute("token") != null) {
			session.setAttribute("login", true);
			if(kakaoservice.duplicateLoginData(kakaoLogindata.getNickname())) {
			}
			else {
				kakaoservice.dataInsert(kakaoLogindata);
			}
			MembersDTO logindata = kakaoservice.getLoginData(kakaoLogindata);
			session.setAttribute("loginMember_img", kakaoLogin_Memberinfo.get("profile_image"));
			session.setAttribute("loginMember", logindata);
		}
		else {
			session.setAttribute("login", false);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {
		
			MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
			model.addAttribute("loginData",loginData);
		
		return "myinfo";
	}
	
	@RequestMapping(value = "/myinfo", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String profile(HttpSession session ,String phone, String address) {
		MembersDTO logindata = (MembersDTO) session.getAttribute("loginMember");
		logindata.setPhone(phone);
		logindata.setAddress(address);
		
		boolean result = service.modifyInfo(logindata);
		JSONObject json = new JSONObject();
		if(result) {
			json.put("status", "success");
			json.put("message", "변경이 완료되었습니다.");
		}
		else {
			json.put("status", "fail");
			json.put("message", "패스워드 변경을 실패했습니다.");
		}
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/myinfo/profileImg", method = RequestMethod.POST)
	public String profileIMG_upload(MultipartFile file, String mediafile, HttpSession session) {
		String saveDirectory = "C:/dev/jee-2021-06/workspace/KH_FinalProj_itbay/itbay/src/main/webapp/resources/img";
		UUID uuid = UUID.randomUUID();
		File saveFile = new File(saveDirectory, uuid.toString() + "_" + file.getOriginalFilename());
		try {
			file.transferTo(saveFile);
			MembersDTO logindata = (MembersDTO) session.getAttribute("loginMember");
			
			ImgDTO login_img_dto = new ImgDTO();
			login_img_dto.setMembers_id(logindata.getId());
			login_img_dto.setImg_name(file.getOriginalFilename());
			
			if(imgservice.updateProfileImg(login_img_dto)) {
				logindata = service.getlogindata(logindata.getId());
				session.removeAttribute("loginMember");
				session.setAttribute("loginMember", logindata);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/myinfo";
	}
}
