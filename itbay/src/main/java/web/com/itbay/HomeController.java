package web.com.itbay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.com.itbay.product.model.ProductDTO;
import web.com.itbay.product.model.ProductService;


@Controller
public class HomeController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		List<ProductDTO> recomend_list = service.selectRecomend();
		model.addAttribute("recomend_list",recomend_list);
		
		List<ProductDTO> viewCount_list = service.selectViewCount();
		model.addAttribute("viewCount_list",viewCount_list);
		
		return "home";
	}
}
