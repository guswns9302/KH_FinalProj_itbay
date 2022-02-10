package web.com.itbay.review_board.Controller.java;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import web.com.itbay.img.model.ImgService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.notice_board.model.Notice_boardDTO;
import web.com.itbay.review_board.model.review_boardDAO;
import web.com.itbay.review_board.model.review_boardDTO;
import web.com.itbay.review_board.model.review_boardService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MembersService;
@Controller

public class review_board {
	
	@Autowired
	review_boardService service;	
	@Autowired
	ImgService imgService;
	
	@RequestMapping(value = "/review_board", method = RequestMethod.GET)
public String review_boardlist(Model model,HttpSession session) {
		List<review_boardDTO> list = service.selectReview();
		model.addAttribute("list", list);
		return "/review_board";
	}
	@RequestMapping(value = "/review_boardDetail", method = RequestMethod.GET)
	public String review_boardDtail() {
			
			return "/review_boardDetail";
	}
	@RequestMapping(value = "/review_boardwrite", method = RequestMethod.GET)
	public String review_boardwrite(Model model,String subject,String contents,String score,HttpSession session) {
		
			return "/review_boardwrite";
		
}
}