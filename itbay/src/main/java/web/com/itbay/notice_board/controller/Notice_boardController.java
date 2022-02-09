package web.com.itbay.notice_board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		model.addAttribute("list", list);
		
		return "/notice_board";
	}
	
}
