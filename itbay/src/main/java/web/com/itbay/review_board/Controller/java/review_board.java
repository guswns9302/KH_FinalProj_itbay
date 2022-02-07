package web.com.itbay.review_board.Controller.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Servlet implementation class review_board
 */
@Controller
public class review_board {
	
	@RequestMapping(value = "/review_board", method = RequestMethod.GET)
public String review_boardlist() {
		
		return "/review_board";
	}
}