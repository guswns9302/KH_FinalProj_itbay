package web.com.itbay.members.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MembersService;

@Controller
public class MembersController {

	@Autowired
	MembersService service;
	
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
}
