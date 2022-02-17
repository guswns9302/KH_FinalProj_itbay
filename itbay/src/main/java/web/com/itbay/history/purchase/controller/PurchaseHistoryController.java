package web.com.itbay.history.purchase.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.com.itbay.history.purchase.model.PurchaseHistoryDTO;
import web.com.itbay.history.purchase.model.PurchaseHistoryService;
import web.com.itbay.img.model.ImgService;
import web.com.itbay.members.model.MembersDTO;

@Controller
public class PurchaseHistoryController {

	@Autowired
	PurchaseHistoryService purchaseService;
	@Autowired
	ImgService imgService;
	
	@RequestMapping(value="/purchase_history", method=RequestMethod.GET)
	public String purchaseHistoryList(Model model, HttpSession session,@RequestParam(value="pageofnum", defaultValue="5") int pageofnum, String page) {
		
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		model.addAttribute("loginData",loginData);
		
		model.addAttribute("pageofnum", pageofnum);
		List<PurchaseHistoryDTO> purchaseList = purchaseService.selectPurchaseHistory(loginData.getId());
		
		model.addAttribute("purchaseList", purchaseList);
		if(page == null) {
			page = "1";
		}
		model.addAttribute("vpage",Integer.parseInt(page));
		return "/purchase_history";
	}
	
	@RequestMapping(value="/purchase_history_np", method=RequestMethod.GET)
	public String purchaseHistoryListNp(Model model, HttpSession session) {
		
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		model.addAttribute("loginData",loginData);
		
		List<PurchaseHistoryDTO> listNp = purchaseService.selectPurchaseNp(loginData.getId());
		model.addAttribute("listNp", listNp);
		
		return "/purchase_history_np";
	}
}
