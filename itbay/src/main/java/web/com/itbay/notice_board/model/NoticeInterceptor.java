package web.com.itbay.notice_board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import web.com.itbay.members.model.MembersDTO;

public class NoticeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		HttpSession session = request.getSession();
		MembersDTO dto = (MembersDTO)session.getAttribute("loginMember");
		
		String requestUrl = request.getRequestURL().toString();
		System.out.println("url 1 :"+requestUrl);
		
		if(requestUrl.contains("_admin")) {
			return true;
		}
		
		if(session.getAttribute("loginMember") != null) {
			System.out.println("url 2 :"+requestUrl);
			System.out.println(dto.getUsername());
			if(dto.getUsername().equals("마스터")) {
				System.out.println("url 3 :"+requestUrl);
				// 아래는 board 올때 마스터 로그인이면 마스터 board로.
				if(requestUrl.contains("notice_board")) {
					response.sendRedirect("/notice_board_admin");
					return false;
				}
				// 아래는 contents 올때 마스터 로그인이면 마스터 board로.
				if(requestUrl.contains("Contents")) {
					response.sendRedirect("/noticeContents_admin");
					return false;
				}
				// admin이 write, modify, delete시 true로
				System.out.println("url 3.5 :"+requestUrl);
				return true;
			} else {
				System.out.println("url 4 :"+requestUrl);
	//			response.sendRedirect("/notice_board_user");
				return true;
			}
		} else {
			System.out.println("url 5 :"+requestUrl);
//		response.sendRedirect("/notice_board");
			return true;
		}	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
