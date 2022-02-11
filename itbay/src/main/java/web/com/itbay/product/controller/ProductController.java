package web.com.itbay.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import web.com.itbay.UploadFileUtils;
import web.com.itbay.img.model.*;
import web.com.itbay.product.model.*;

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
		model.addAttribute("subject", subject);
		model.addAttribute("sold_out", sold_out);
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
	
	@RequestMapping(value="/productWrite", method = RequestMethod.GET)
	public String productWrite(Model model) {
		return "/product_write";
	}
	
	@RequestMapping(value="/productSave", method = RequestMethod.POST)
	public String productSave(ProductDTO productDto, @RequestParam("file") List<MultipartFile> file) throws Exception{
			
		productService.productSave(productDto, file);
		
		return "redirect:/product";
	}
	
}
