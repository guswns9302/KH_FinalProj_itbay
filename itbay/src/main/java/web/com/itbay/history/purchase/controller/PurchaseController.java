package web.com.itbay.history.purchase.controller;


import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import web.com.itbay.history.purchase.model.PurchaseDTO;
import web.com.itbay.history.purchase.model.PurchaseHistoryDTO;
import web.com.itbay.history.purchase.model.PurchaseService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MembersService;
import web.com.itbay.members.model.MileageDTO;
import web.com.itbay.members.model.MileageService;

@Controller
public class PurchaseController {
	
	@Autowired
	PurchaseService service;
	
	@Autowired
	MembersService membersService;
	
	@Autowired
	MileageService mileageService;
	
	@RequestMapping(value="/purchase_product", method=RequestMethod.GET)
	public String purchaseProduct(Model model, HttpSession session, int product_id) {
		PurchaseDTO productInfo = service.getProductInfo(product_id);
		model.addAttribute("productInfo",productInfo);
		session.setAttribute("productInfo", productInfo);
		return "/purchase_product";
	}
	@ResponseBody
	@RequestMapping(value="/purchase_product_info", method=RequestMethod.POST,  produces = "text/html; charset=utf8")
	public String purchaseProductInfo(Model model, HttpSession session, int product_id, int price) {
		MembersDTO purchaseMember = (MembersDTO) session.getAttribute("loginMember");
		PurchaseHistoryDTO dto = new PurchaseHistoryDTO();
		dto.setProduct_id(product_id);
		dto.setPrice(-price);
		dto.setMembers_id(purchaseMember.getId());
		
		MileageDTO userMileageAmount = mileageService.getAmount_mileage(purchaseMember.getId());
		if( userMileageAmount.getMileage_amount() > price) {
			MileageDTO useMileage = new MileageDTO(dto.getMembers_id(), dto.getPrice());
			boolean result_purchaseHistory = service.insertPurchaseHistory(dto);
			boolean result_salesHistory = service.insertsalesHistory(dto);
			boolean result_update = service.updateProductSoldOut(dto.getProduct_id());
			boolean result_update_mileage = mileageService.chargeMileage(useMileage);
			if(result_purchaseHistory && result_salesHistory && result_update && result_update_mileage) {
				session.removeAttribute("productInfo");
				return "<script>alert('????????? ?????????????????????. ?????????????????? ???????????????.'); location.href='/purchase_history';</script>";
			}
			else {
				return "<script>alert('????????? ??????????????????. ??????????????? ???????????????. '); location.href='/';</script>";
			}
		}
		else {
			return "<script>alert('??????????????? ???????????????. ??????????????? ???????????????.'); location.href='/mileage';</script>";
		}
		
	}
	
	@RequestMapping(value = "/purchase_myinfo", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String profile(HttpSession session ,String phone, String address) {
		MembersDTO logindata = (MembersDTO) session.getAttribute("loginMember");
		logindata.setPhone(phone);
		logindata.setAddress(address);
		
		boolean result = membersService.modifyInfo(logindata);
		JSONObject json = new JSONObject();
		if(result) {
			json.put("status", "success");
			json.put("message", "????????? ?????????????????????.");
		}
		else {
			json.put("status", "fail");
			json.put("message", "???????????? ????????? ??????????????????.");
		}
		return json.toJSONString();
	}
}
