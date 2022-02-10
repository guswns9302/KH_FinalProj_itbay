package web.com.itbay.notice_board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.notice_board.model.Notice_boardDTO;
import web.com.itbay.notice_board.model.Notice_boardService;

@Controller
public class Notice_boardController {
	
	@Autowired
	Notice_boardService service;
	
	@RequestMapping(value="/notice_board", method=RequestMethod.GET)
	public String notice(Model model) {
		System.out.println("notice controller");
		List<Notice_boardDTO> list = service.getNotice();

		List<MembersDTO> admin = service.getMembers();
		
		model.addAttribute("list", list);
		model.addAttribute("admin", admin);
		
		return "/notice_board";
	}
	
	@RequestMapping(value="/noticeContents", method=RequestMethod.GET)
	public String noticeContents(Model model, String noticeId) {
		//@RequestParam(noticeId) int noticeId -> 형변환 없이 바로 사용 가능
		System.out.println("noticeContents controller");
		int id = Integer.parseInt(noticeId);
				
		Notice_boardDTO dto = service.getNoticeContents(id);
		List<MembersDTO> admin = service.getMembers();
		
		model.addAttribute("dto", dto);
		model.addAttribute("admin", admin);
		
		return "/noticeContents";
	}
	
	@RequestMapping(value="/noticeModify", method=RequestMethod.GET)
	public String noticeModify(Model model, HttpSession session, 
			String subject, String contents
			) {
		//noticeContents에서 기존 정보 가져오기. subject, contents
		System.out.println("noticeModify controller");
		System.out.println("subject : " + subject);
		System.out.println("contents : " + contents);
		Notice_boardDTO dto = new Notice_boardDTO(subject, contents);
		
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
		int res = service.noticeSave(dto);
		
		if(res == 1) {
			System.out.println("저장성공");
			return "/notice_board";
		} else {
			System.out.println("저장실패");
			//에러메시지 포함해서 보내주기
			return "redirect:/noticeWrite";
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
			return "/notice_board";
		} else {
			System.out.println("삭제실패");
			//에러메시지 포함해서 보내주기
			return "redirect:/noticeContents";
		}
	}
	
	
	
}
