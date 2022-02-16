package web.com.itbay.members.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MileageDTO;
import web.com.itbay.members.model.MileagePagingDTO;
import web.com.itbay.members.model.MileageService;

@Controller
public class MileageController {

	@Autowired
	MileageService service;
	
	@RequestMapping(value = "/mileage", method = RequestMethod.GET)
	public String mileage(Model model, HttpSession session, String vpage, String numPerPage) {
		
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		model.addAttribute("loginData",loginData);
		
		MileageDTO amount_mileage = service.getAmount_mileage(loginData.getId());
		model.addAttribute("mileage",amount_mileage);
		
		int total_page = 0;
		if(numPerPage == null) {
			numPerPage = "5";
		}
		if(vpage == null) {
			vpage = "1";
		}
		int now_page = Integer.parseInt(vpage);
		MileagePagingDTO pagingdto = service.getTotalPost(loginData.getId());
		pagingdto.setMembers_id(loginData.getId());
		pagingdto.setNumPerPage(Integer.parseInt(numPerPage));
		
		pagingdto.setQueryNum((now_page-1) * pagingdto.getNumPerPage());
		
		if(pagingdto.getTotalPost() % pagingdto.getNumPerPage() == 0) {
			total_page = pagingdto.getTotalPost() / pagingdto.getNumPerPage();
		}
		else {
			total_page = pagingdto.getTotalPost() / pagingdto.getNumPerPage() + 1; 
		}
		
		List<MileageDTO> history_mileage = service.getHistory_mileage(pagingdto);
		
		model.addAttribute("total_page",total_page);
		model.addAttribute("vpage",vpage);
		model.addAttribute("numPerPage",pagingdto.getNumPerPage());
		model.addAttribute("history_mileage",history_mileage);
		
		return "/mileage";
	}
	
	@RequestMapping(value = "/mileage/charge", method = RequestMethod.POST)
	public String charge_mileage(Model model, HttpSession session, int mileage_amount) {
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		model.addAttribute("loginData",loginData);
		
		MileageDTO charge_mileage = new MileageDTO(loginData.getId(), mileage_amount);
		boolean result = service.chargeMileage(charge_mileage);
		if(result) {
			System.out.println("마일리지 충전 : " + mileage_amount);
			return "redirect:/mileage";
		}
		else {
			System.out.println("마일리지 충전 실패");
			return "redirect:/mileage";
		}
	}
}
