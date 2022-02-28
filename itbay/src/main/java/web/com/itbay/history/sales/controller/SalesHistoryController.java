package web.com.itbay.history.sales.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public String salesHistoryList(Model model, @RequestParam(value="pageofnum", defaultValue="5") int pageofnum, String page) {
		model.addAttribute("pageofnum", pageofnum);
		List<SalesHistoryDTO> salesList = salesService.selectSalesHistory();
		model.addAttribute("salesList", salesList);
		
		int total_price = 0;
		for(int i = 0; i < salesList.size(); i++) {
			total_price += salesList.get(i).getPrice();
		}
		model.addAttribute("total_price",total_price);
		
		if(page == null) {
			page = "1";
		}
		model.addAttribute("vpage",Integer.parseInt(page));
		return "/sales_history";
	}
	
	@RequestMapping(value="/sales_history_np", method=RequestMethod.GET)
	public String SalesHistoryListNp(Model model, HttpSession session) {
		List<SalesHistoryDTO> sListNp = salesService.selectSalesNp();
		model.addAttribute("sListNp", sListNp);
		return "/sales_history_np";
	}
}
