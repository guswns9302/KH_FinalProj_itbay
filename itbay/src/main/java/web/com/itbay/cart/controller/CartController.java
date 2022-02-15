package web.com.itbay.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.CookieGenerator;

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
	public String cart(Model model) {
		return "/cart";
	}
}
