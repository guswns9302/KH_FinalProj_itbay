package web.com.itbay.review_boardwrite.Controller;

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
public class review_boardwrite {
	
	@RequestMapping(value = "/review_boardwrite", method = RequestMethod.GET)
public String review_boardwrite() {
		
		return "/review_boardwrite";
	}
}
