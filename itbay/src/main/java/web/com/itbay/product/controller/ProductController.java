package web.com.itbay.product.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import web.com.itbay.img.model.*;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.product.model.*;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ImgService imgService;
	

	@RequestMapping(value="/product", method = RequestMethod.GET)
	public String product(HttpServletRequest request, Model model, String subject, String sold_out, @RequestParam(defaultValue = "0") int page) {
		
		List<ProductDTO> list = productService.selectProduct(subject, sold_out, page, model);



		model.addAttribute("list", list);
		model.addAttribute("subject", subject);
		model.addAttribute("sold_out", sold_out);
		
		HttpSession session = request.getSession();
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		
		if(loginData != null) {
			model.addAttribute("loginData",loginData);
		}
		

		return "/product";
	}
	
	@RequestMapping(value="/productDetail", method = RequestMethod.GET)
	public String productDetail(HttpServletRequest request, Model model, String product_id) {
		
		int id = Integer.parseInt(product_id);
		
		
		
		List<ImgDTO> imgList = imgService.selectImg(id);
		ProductDTO productDto = productService.selectProductDetail(id);
		
		model.addAttribute("imgList", imgList);
		model.addAttribute("productDto", productDto);
		
		
		HttpSession session = request.getSession();
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		
		if(loginData != null) {
			model.addAttribute("loginData",loginData);
		}		
		
		return "/productDetail";
	}
	
	
	@RequestMapping(value="/productWrite", method = RequestMethod.GET)
	public String productWrite(Model model, HttpServletRequest request) throws Exception{
		
		return "/product_write";
	}
	
	@RequestMapping(value="/productSave", method = RequestMethod.POST)
	public void productSave(HttpServletResponse response, ProductDTO productDto, @RequestParam("file") List<MultipartFile> file) throws Exception{
			
		productService.productSave(productDto, file);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		 
		out.println("<script>alert('등록되었습니다.'); location.href='/product';</script>");
		

	}
	
	@RequestMapping(value="/productUpdate", method = RequestMethod.POST)
	public void productUpdate(HttpServletResponse response, ProductDTO productDto) throws Exception{
		productService.productUpdate(productDto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		 
		out.println("<script>alert('수정되었습니다.'); location.href='/product';</script>");		
		
	}
	
	
	@RequestMapping(value="/productDelete", method = RequestMethod.GET)
	public void productDelete(HttpServletResponse response, int id) throws Exception{
		productService.productDelete(id);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		 
		out.println("<script>alert('삭제되었습니다.'); location.href='/product';</script>");	
		
	}	
	
	
	
	
	
	
	
}
