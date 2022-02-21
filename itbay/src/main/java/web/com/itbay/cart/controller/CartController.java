package web.com.itbay.cart.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.CookieGenerator;

import web.com.itbay.cart.model.CartDTO;
import web.com.itbay.cart.model.CartService;
import web.com.itbay.members.model.MembersDTO;

@Controller
public class CartController {

	@Autowired
	CartService cartService;
	
	
	@RequestMapping("/add/cart")
	public @ResponseBody boolean addCart(HttpServletRequest request, HttpServletResponse response, int id) {
		HttpSession session = request.getSession();
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		
		if (loginData == null) {
			
			CookieGenerator cart = new CookieGenerator();
			cart.setCookieName("product"+id);
			cart.addCookie(response, id+"");
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(id);
			cartService.addCart(list, loginData.getId());
			
		}
		
		return true;
	}
	
	@RequestMapping(value="/cart", method = RequestMethod.GET)
	public String cart(HttpServletRequest request, Model model, @RequestParam(defaultValue = "0") int members_id) {
		
		HttpSession session = request.getSession();
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		
		if(loginData != null) {			
			List<CartDTO> list = cartService.selectCart(members_id);
			
			List<HashMap> allPur = new ArrayList<HashMap>();
			for(CartDTO cart : list) {
				if(cart.getOrder_status().equals("N")) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("id", cart.getProduct_id());
					map.put("price", cart.getPrice());
					allPur.add(map);					
				}
			}
			model.addAttribute("allPur", allPur);
			model.addAttribute("list", list);
			
		} else {
			
			// 마솔 - 비회원 장바구니 기능 추가 시작
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				List<Integer> idList = new ArrayList<Integer>();
				for(Cookie cookie : cookies) {
					if(!cookie.getName().equals("JSESSIONID")) {
						idList.add(Integer.parseInt(cookie.getValue()));
					}
				}
				
				
				List<CartDTO> list = cartService.selectCookieProductBoard(idList);
				model.addAttribute("list", list);
			}

		}
		
		
		
		
		return "/cart";
	}
	
	@RequestMapping(value="/deleteCart", method = RequestMethod.GET)
	public void deleteCart(HttpServletResponse response, int id, int members_id) throws Exception{
		cartService.deleteCart(id);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		 
		out.println("<script>alert('삭제되었습니다.'); location.href='/cart?members_id="+members_id+"';</script>");	
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/cart/all/pur", method = RequestMethod.POST)
	public void cartAllPur(@RequestParam(value = "param") List<Map<String, Object>> param) {
		
		for(Map<String, Object> ob : param) {
			System.out.println(ob.get("id"));
			System.out.println(ob.get("price"));
		}

		
	}
	
}
