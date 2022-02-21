package web.com.itbay.memberList.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import web.com.itbay.history.purchase.model.PurchaseHistoryDTO;
import web.com.itbay.memberList.model.MemberListService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.notice_board.paging.Pagination;

@Controller
public class MemberListController {
	
	@Autowired
	MemberListService service;
	
	@RequestMapping(value="/member_list", method = RequestMethod.GET)
	public String memberlist(@RequestParam(name="page", defaultValue="1") int page,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		//페이징
		String pageListCnt = "5";
		if(request.getParameter("cnt") != null) {
			pageListCnt = request.getParameter("cnt");
		} else {
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("pageListCnt")) {
					pageListCnt = cookie.getValue();
				}
			}
		}
		
		Cookie cookie = new Cookie("pageListCnt", pageListCnt);
		cookie.setPath(request.getRequestURI());
		response.addCookie(cookie);
		
		int maxCnt = service.countingMember();//전체 회원 수
		
		Pagination<MembersDTO> paging = new Pagination<MembersDTO>(
				maxCnt, Integer.parseInt(pageListCnt));
		try {
			Map<String, Integer> range = paging.getPage(page);
			List<MembersDTO> datas = service.selectMember(range);
			model.addAttribute("datas", datas);
			model.addAttribute("pageList", paging.getPageList());
			model.addAttribute("pageListCnt", pageListCnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/member_list";
	}

	@RequestMapping(value="/member_purchase_history", method = RequestMethod.GET)
	public String memberPurchaseHistory(@RequestParam(name="page", defaultValue="1") int page,
			Model model, String membersId,
			HttpServletRequest request, HttpServletResponse response) {
		
		//페이징
		String pageListCnt = "5";
		if(request.getParameter("cnt") != null) {
			pageListCnt = request.getParameter("cnt");
		} else {
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("pageListCnt")) {
					pageListCnt = cookie.getValue();
				}
			}
		}
		
		Cookie cookie = new Cookie("pageListCnt", pageListCnt);
		cookie.setPath(request.getRequestURI());
		response.addCookie(cookie);
		
		int maxCnt = service.countingHistories();//전체 회원 수
		
		Pagination<PurchaseHistoryDTO> paging = new Pagination<PurchaseHistoryDTO>(
				maxCnt, Integer.parseInt(pageListCnt));
		try {
		 	Map<String, Integer> range = paging.getPage(page);
		 	int members_id = Integer.parseInt(membersId);
		 	range.put("membersId", members_id);
		 	List<PurchaseHistoryDTO> histories = service.selectHistories(range);
			model.addAttribute("histories", histories);
			System.out.println("size :" + histories.size());
			model.addAttribute("pageList", paging.getPageList());
			model.addAttribute("pageListCnt", pageListCnt);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return "/member_purchase_history";
	}
}
