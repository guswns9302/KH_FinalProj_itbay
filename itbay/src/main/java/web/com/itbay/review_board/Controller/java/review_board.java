package web.com.itbay.review_board.Controller.java;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import web.com.itbay.img.model.ImgDTO;
import web.com.itbay.img.model.ImgService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.notice_board.model.Notice_boardDTO;
import web.com.itbay.product.model.ProductDTO;
import web.com.itbay.review_board.model.review_boardDAO;
import web.com.itbay.review_board.model.review_boardDTO;
import web.com.itbay.review_board.model.review_boardService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MembersService;
@Controller

public class review_board<review_boardDto> {
	
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
	@RequestMapping(value="/review_boardDetail", method = RequestMethod.GET)
	public String reviewdetail(Model model, String reviewid) {
		int id = Integer.parseInt(reviewid);	
		List<ImgDTO> imgList = imgService.selectImg(id);
		
		review_boardDTO dto=service.getselectreviewDetail(id);
		
		model.addAttribute("imgList", imgList);
		model.addAttribute("dto",dto);	
		return "/review_boardDetail";
	}

	  @RequestMapping(value="/review_boardwrite")
	    public String review_boardwrite(HttpSession session) throws Exception {
	        return "/review_boardwrite";
	    }
	  @RequestMapping(value="/review_boardinsert")
	    public ModelAndView reviewboardInsert(review_boardDTO review_boardDto,HttpSession session,Model model) throws Exception {
		  ModelAndView review = new ModelAndView("redirect:/review_board");
	      // dto  = new dto   
		  // session -> 회원정보 가져오면 [ members_id ]
		  // dto.setMembers_id(session.getid());
		  
		  	service.insertreview(review_boardDto);
	        return review;
	    }
}
