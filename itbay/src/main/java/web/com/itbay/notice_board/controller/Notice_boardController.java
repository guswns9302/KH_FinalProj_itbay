package web.com.itbay.notice_board.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.notice_board.model.Notice_boardDTO;
import web.com.itbay.notice_board.model.Notice_boardService;
import web.com.itbay.notice_board.paging.Pagination;

@Controller
public class Notice_boardController {
	
	@Autowired
	Notice_boardService service;
	
	@RequestMapping(value="/notice_board", method=RequestMethod.GET)
	public String notice(@RequestParam(name="page", defaultValue="1") int page, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<MembersDTO> members = service.getMembers();
		model.addAttribute("members", members);
		
		//paging 추가
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
		
		int maxCnt = service.countingNotice();//전체 게시글 수
		Pagination<Notice_boardDTO> paging = new Pagination<Notice_boardDTO>(maxCnt, Integer.parseInt(pageListCnt));
		try {
			List<Notice_boardDTO> datas = service.selectPage(paging.getPage(page));
			model.addAttribute("datas", datas);
			model.addAttribute("pageList", paging.getPageList());
			model.addAttribute("pageListCnt", pageListCnt);
			model.addAttribute("vpage",page);
			model.addAttribute("total_page", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/noticeboard_user";
	}
	
	@RequestMapping(value="/noticeContents_user", method=RequestMethod.GET)
	public String noticeContents(Model model, String noticeId) {
		int id = Integer.parseInt(noticeId);
		Notice_boardDTO dto = service.getNoticeContents(id);
		model.addAttribute("dto", dto);
		return "/noticeContents_user";
	}
	
	@RequestMapping(value="/notice_board_admin", method=RequestMethod.GET)
	public String noticeAdmin(@RequestParam(name="page", defaultValue="1") int page, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<MembersDTO> members = service.getMembers();
		model.addAttribute("members", members);
		//paging 추가
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
		
		int maxCnt = service.countingNotice();
		Pagination<Notice_boardDTO> paging = new Pagination<Notice_boardDTO>(
				maxCnt, Integer.parseInt(pageListCnt));
		try {
			List<Notice_boardDTO> datas = service.selectPage(paging.getPage(page));
			model.addAttribute("datas", datas);
			model.addAttribute("pageList", paging.getPageList());
			model.addAttribute("pageListCnt", pageListCnt);
			model.addAttribute("vpage",page);
			model.addAttribute("total_page", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/noticeboard_admin";
	}
	
	@RequestMapping(value="/noticeContents_admin", method=RequestMethod.GET)
	public String noticeContentsAdmin(Model model, String noticeId) {
		int id = Integer.parseInt(noticeId);
		Notice_boardDTO dto = service.getNoticeContents(id);
		List<MembersDTO> admin = service.getMembers();
		model.addAttribute("dto", dto);
		model.addAttribute("admin", admin);
		
		return "/noticeContents_admin";
	}
	
	@RequestMapping(value="/noticeModify", method=RequestMethod.GET)
	public String noticeModify(Model model, HttpSession session, String noticeId, String subject, String contents) {
		int id = Integer.parseInt(noticeId);
		Notice_boardDTO dto = new Notice_boardDTO(id, subject, contents);
		model.addAttribute("dto", dto);
		return "/noticeModify";
	}
	
	@RequestMapping(value="/noticeWrite", method=RequestMethod.GET)
	public String noticeWrite(Model model) {
		return "/noticeWrite";
	}
	
	@RequestMapping(value="/noticeSave", method=RequestMethod.POST)
	public String noticeSave(Model model, HttpSession session, String subject, String contents) {
		Notice_boardDTO dto = new Notice_boardDTO(subject, contents);
		int res = service.noticeSave(dto);
		
		if(res == 1) {
			return "redirect:/notice_board";
		} else {
			return "forward:/noticeWrite";
		}
	}
	
	@RequestMapping(value="/noticeChange", method=RequestMethod.POST)
	public String noticeChange(Model model, HttpSession session, 
			String noticeId, String subject, String contents) {
		int id = Integer.parseInt(noticeId);
		
		Notice_boardDTO dto = new Notice_boardDTO(id, subject, contents);
		
		int res = service.noticeChange(dto);
		
		if(res == 1) {
			return "redirect:/notice_board";
		} else {
			System.out.println("수정실패");
			return "forward:/noticeModify";
		}
	}
	
	@RequestMapping(value="/noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(Model model, String noticeId) {
		int id = Integer.parseInt(noticeId);
		boolean res = service.noticeDelete(id);
		if(res) {
			return "redirect:/notice_board";
		} else {
			return "forward:/noticeContents_admin";
		}
	}
}
