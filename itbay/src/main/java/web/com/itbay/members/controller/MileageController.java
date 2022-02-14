package web.com.itbay.members.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MileageDTO;
import web.com.itbay.members.model.MileageService;

@Controller
public class MileageController {

	@Autowired
	MileageService service;
	
	@RequestMapping(value = "/mileage", method = RequestMethod.GET)
	public String mileage(Model model, HttpSession session) {
		
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		model.addAttribute("loginData",loginData);
		
		MileageDTO amount_mileage = service.getAmount_mileage(loginData.getId());
		model.addAttribute("mileage",amount_mileage);
		
		return "/mileage";
	}
	
	@RequestMapping(value = "/mileage/charge", method = RequestMethod.POST)
	public String charge_mileage(Model model, HttpSession session, int mileage_amount) {
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		model.addAttribute("loginData",loginData);
		
		MileageDTO amount_mileage = service.getAmount_mileage(loginData.getId());
		model.addAttribute("mileage",amount_mileage);
		
		System.out.println(mileage_amount);
		
		return "redirect:/mileage";
	}
}
