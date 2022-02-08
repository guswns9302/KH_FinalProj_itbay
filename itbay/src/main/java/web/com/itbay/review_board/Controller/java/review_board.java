package web.com.itbay.review_board.Controller.java;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.com.itbay.review_board.model.review_boardDAO;
import web.com.itbay.review_board.model.review_boardDTO;
import web.com.itbay.review_board.model.review_boardService;

@Controller

public class review_board {
	
	@Autowired
	review_boardService reviews;	

	@RequestMapping(value = "/review_board", method = RequestMethod.GET)
public String review_boardlist() {		
		return "/review_board";
	}
	@RequestMapping(value = "/review_boardDtail", method = RequestMethod.GET)
	public String review_boardDtail() {
			
			return "/review_boardDtail";
	}
	@RequestMapping(value = "/review_boardwrite", method = RequestMethod.GET)
	public String review_boardwrite(String subject,String contents,String score) {
	int scores=Integer.parseInt(score);
		review_boardDTO dto =new review_boardDTO(subject,contents,scores);
		review_boardDTO review=reviews.review_board(dto);
		if(review!=null)
		{
			return "/review_board";
		}
		else
		{
			return "/review_boardwrite";
		}
}
}