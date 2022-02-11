package web.com.itbay.history.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import web.com.itbay.history.sales.model.SalesHistoryDTO;
import web.com.itbay.history.sales.model.SalesHistoryService;
import web.com.itbay.img.model.ImgService;

@Controller
public class SalesHistoryController {

	@Autowired
	SalesHistoryService salesService;
	@Autowired
	ImgService imgService;
	
	@RequestMapping(value="/sales_history", method=RequestMethod.GET)
	public String purchaseHistoryList(Model model, @RequestParam(defaultValue="5") int pageofnum) {
		model.addAttribute("pageofnum", pageofnum);
		List<SalesHistoryDTO> salesList = salesService.selectSalesHistory();
		
		model.addAttribute("salesList", salesList);

		return "/sales_history";
	}
}
