package web.com.itbay.review_board.Controller.java;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class review_board {
	
	
	@RequestMapping(value = "/review_board", method = RequestMethod.GET)
public String review_boardlist() {
		
		return "/review_board";
	}

	@RequestMapping(value = "/review_boardwrite", method = RequestMethod.GET)
	public String review_boardwrite() {
			
			return "/review_boardwrite";
		}
	@Autowired
	SqlSession session;
	
	
}