package web.com.itbay.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.com.itbay.img.model.ImgDTO;
import web.com.itbay.img.model.ImgService;
import web.com.itbay.product.model.ProductDTO;
import web.com.itbay.product.model.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	ImgService imgService;
	
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public String product(Model model, String subject, String sold_out) {
		List<ProductDTO> list = productService.selectProduct(subject, sold_out);

		model.addAttribute("list", list);
		
		return "/product";
	}
	
	@RequestMapping(value="/productDetail", method = RequestMethod.GET)
	public String productDetail(Model model, String product_id) {
		
		int id = Integer.parseInt(product_id);
		
		
		
		List<ImgDTO> imgList = imgService.selectImg(id);
		ProductDTO productDto = productService.selectProductDetail(id);
		
		model.addAttribute("imgList", imgList);
		model.addAttribute("productDto", productDto);
		
		return "/productDetail";
	}
	
}
