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
	public String notice(@RequestParam(name="page", defaultValue="1") int page,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("notice controller");
//		List<Notice_boardDTO> list = service.getNotice();
//		model.addAttribute("list", list);
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
		System.out.println("cnt : " + pageListCnt);
		
		Cookie cookie = new Cookie("pageListCnt", pageListCnt);
		cookie.setPath(request.getRequestURI());
		response.addCookie(cookie);
		
		int maxCnt = service.countingNotice();
		Pagination<Notice_boardDTO> paging = new Pagination<Notice_boardDTO>(
				maxCnt, Integer.parseInt(pageListCnt));
		try {
			List<Notice_boardDTO> datas = service.selectPage(paging.getPage(page));
			System.out.println("size :" + datas.size());
			model.addAttribute("datas", datas);
			model.addAttribute("pageList", paging.getPageList());
			model.addAttribute("pageListCnt", pageListCnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("before return at Controller");
		return "/noticeboard_user";
	}
	
	@RequestMapping(value="/noticeContents_user", method=RequestMethod.GET)
	public String noticeContents(Model model, String noticeId) {
		//@RequestParam(noticeId) int noticeId -> 형변환 없이 바로 사용 가능
		System.out.println("noticeContents controller");
		int id = Integer.parseInt(noticeId);
				
		Notice_boardDTO dto = service.getNoticeContents(id);
		List<MembersDTO> admin = service.getMembers();
		
		model.addAttribute("dto", dto);
		model.addAttribute("admin", admin);
		
		return "/noticeContents_user";
	}
	
	@RequestMapping(value="/notice_board_admin", method=RequestMethod.GET)
	public String noticeAdmin(@RequestParam(name="page", defaultValue="1") int page,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("noticeAdmin controller");
//		List<Notice_boardDTO> list = service.getNotice();
//		model.addAttribute("list", list);
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
		System.out.println("cnt : " + pageListCnt);
		
		Cookie cookie = new Cookie("pageListCnt", pageListCnt);
		cookie.setPath(request.getRequestURI());
		response.addCookie(cookie);
		
		int maxCnt = service.countingNotice();
		Pagination<Notice_boardDTO> paging = new Pagination<Notice_boardDTO>(
				maxCnt, Integer.parseInt(pageListCnt));
		try {
			List<Notice_boardDTO> datas = service.selectPage(paging.getPage(page));
			System.out.println("size :" + datas.size());
			model.addAttribute("datas", datas);
			model.addAttribute("pageList", paging.getPageList());
			model.addAttribute("pageListCnt", pageListCnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/noticeboard_admin";
	}
	
	@RequestMapping(value="/noticeContents_admin", method=RequestMethod.GET)
	public String noticeContentsAdmin(Model model, String noticeId) {
		//@RequestParam(noticeId) int noticeId -> 형변환 없이 바로 사용 가능
		System.out.println("noticeContentsAdmin controller");
		int id = Integer.parseInt(noticeId);
		
		Notice_boardDTO dto = service.getNoticeContents(id);
		List<MembersDTO> admin = service.getMembers();
		
		model.addAttribute("dto", dto);
		model.addAttribute("admin", admin);
		
		return "/noticeContents_admin";
	}
	
	@RequestMapping(value="/noticeModify", method=RequestMethod.GET)
	public String noticeModify(Model model, HttpSession session, 
			String noticeId, String subject, String contents
			) {
		//noticeContents에서 기존 정보 가져오기. subject, contents
		System.out.println("noticeModify controller");
		System.out.println("noticeId : " + noticeId);
		System.out.println("subject : " + subject);
		System.out.println("contents : " + contents);
		//id만 가지고 찾아도 되지 않나?
		//1. dto에 id까지 넣어서 dto 보내기->db 안들어감
		//2. id로 db 들어가서 dto 찾아오기
		int id = Integer.parseInt(noticeId);
		Notice_boardDTO dto = new Notice_boardDTO(id, subject, contents);
		
		model.addAttribute("dto", dto);
		
		return "/noticeModify";
	}
	
	@RequestMapping(value="/noticeWrite", method=RequestMethod.GET)
	public String noticeWrite(Model model) {
		//@RequestParam(noticeId) int noticeId -> 형변환 없이 바로 사용 가능
		System.out.println("noticeWrite controller");
		return "/noticeWrite";
	}
	
	@RequestMapping(value="/noticeSave", method=RequestMethod.POST)
	public String noticeSave(Model model, HttpSession session, 
			String subject, String contents) {
		System.out.println("noticeSave controller");
		System.out.println("subject : " + subject);
		System.out.println("contents : " + contents);
		
		Notice_boardDTO dto = new Notice_boardDTO(subject, contents);
		//아래 부분을 업데이트로. 업데이트 하려면 알아야 할 필수 정보. dto.id
		int res = service.noticeSave(dto);
		
		if(res == 1) {
			System.out.println("저장성공");
			return "redirect:/notice_board";
		} else {
			System.out.println("저장실패");
			//에러메시지 포함해서 보내주기
			return "forward:/noticeWrite";
		}
	}
	
	@RequestMapping(value="/noticeChange", method=RequestMethod.POST)
	public String noticeChange(Model model, HttpSession session, 
			String noticeId, String subject, String contents) {
		System.out.println("noticeChange controller");
		System.out.println("noticeId : " + noticeId);
		System.out.println("subject : " + subject);
		System.out.println("contents : " + contents);
		
		int id = Integer.parseInt(noticeId);
		
		Notice_boardDTO dto = new Notice_boardDTO(id, subject, contents);
		
		int res = service.noticeChange(dto);
		
		if(res == 1) {
			System.out.println("수정성공");
			return "redirect:/notice_board";
		} else {
			System.out.println("수정실패");
			//에러메시지 포함해서 보내주기
			return "forward:/noticeModify";
		}
	}
	
	
	
	@RequestMapping(value="/noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(Model model, String noticeId) {
		//@RequestParam(noticeId) int noticeId -> 형변환 없이 바로 사용 가능
		System.out.println("noticeDelete controller");
		int id = Integer.parseInt(noticeId);
		
		boolean res = service.noticeDelete(id);
		
		if(res) {
			System.out.println("삭제성공");
			List<Notice_boardDTO> list = service.getNotice();
			List<MembersDTO> admin = service.getMembers();
			
			model.addAttribute("list", list);
			model.addAttribute("admin", admin);
			return "redirect:/notice_board";
		} else {
			System.out.println("삭제실패");
			//에러메시지 포함해서 보내주기
			return "forward:/noticeContents_admin";
		}
	}
	
	
	
}
