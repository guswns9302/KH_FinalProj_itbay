package web.com.itbay.history.purchase.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.com.itbay.history.purchase.model.PurchaseHistoryDTO;
import web.com.itbay.history.purchase.model.PurchaseHistoryService;
import web.com.itbay.img.model.ImgService;

@Controller
public class PurchaseHistoryController {

	@Autowired
	PurchaseHistoryService purchaseService;
	@Autowired
	ImgService imgService;
	
	@RequestMapping(value="/purchase_history", method=RequestMethod.GET)
	public String purchaseHistoryList(Model model, int id) {
		System.out.println("purchaseHistoryList 메서드 실행합니다.");
		
		List<PurchaseHistoryDTO> purchaseList = purchaseService.getAllPurchaseHistory(id);
		
		model.addAttribute("purchaseList", purchaseList);
		
		return "/purchase_history";
	}
}
