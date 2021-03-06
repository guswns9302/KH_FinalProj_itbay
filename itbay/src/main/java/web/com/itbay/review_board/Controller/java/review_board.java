package web.com.itbay.review_board.Controller.java;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.com.itbay.history.purchase.model.PurchaseHistoryService;
import web.com.itbay.img.model.ImgService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.product.model.ProductDTO;
import web.com.itbay.review_board.model.NewreviewDTO;
import web.com.itbay.review_board.model.commentDTO;
import web.com.itbay.review_board.model.review_boardDTO;
import web.com.itbay.review_board.model.review_boardService;

@Controller

public class review_board<review_boardDto> {
	

	@Autowired
	PurchaseHistoryService purchaseService;
	@Autowired
	review_boardService service;	
	@Autowired
	ImgService imgService;
	
	@RequestMapping(value = "/review_board", method = RequestMethod.GET)
	public String review_boardlist(Model model,HttpSession session,@RequestParam(value="pageofnum", defaultValue="5") int pageofnum, String page) {
		model.addAttribute("pageofnum", pageofnum);
		List<review_boardDTO> list = service.selectReview();
		model.addAttribute("list", list);
		if(page == null) {
			page = "1";
		}
		model.addAttribute("vpage",Integer.parseInt(page));
		return "/review_board";
	}
	@RequestMapping(value="/review_boardDetail", method = RequestMethod.GET)
	public String reviewdetail(Model model, String reviewid) {
		int id = Integer.parseInt(reviewid);
		
		List<commentDTO> comments=service.getComment(id);
		System.out.println(id);
		review_boardDTO dto=service.getselectreviewDetail(id);
		service.inviewcnt(id);
		model.addAttribute("dto",dto);
		model.addAttribute("comments",comments);
	
		return "/review_boardDetail";
	}

	  @RequestMapping(value="/review_boardWrite")
	    public String review_boardwrite(Model model,HttpSession session,String etc) throws Exception {
		  int ids =Integer.parseInt(etc);
	      System.out.print(ids); 
		  ProductDTO product_data = service.getproductdata(ids);
		   //???????????? select * from product_board where id = #{id};
		   // ?????? , ???????????? id , ????????????
		  model.addAttribute("productDatad",product_data);
		   // ${prodcutData.getPrice()}
		  session.setAttribute("product_data", product_data);
		  return "/review_boardwrite";
	    }
	  @RequestMapping(value="/review_boardinsert")
	    public ModelAndView reviewboardInsert(Model model, HttpSession session,HttpServletRequest request) throws Exception {
		  ModelAndView review = new ModelAndView("redirect:/review_board");
		  	
		  	//-----
		  	session.getAttribute("prodcut_data");
		  	session.getAttribute("loginMember");
		  	// members_id --> ???
		  	// product_id, price, ???????????? --> ??????
 		  	// ?????? ?????? ?????? --> ???????????? ???
		  	// ???????????? ??????????????? dto??? ???????????? ???
		  	//members_id prduct_id price ????????????, ?????? ?????? ??????re
		  	System.out.println("??????????????? ?????? ????????? ??????");
		  	System.out.println(request.getParameter("members_id"));
		  	System.out.println(request.getParameter("subject"));
		  	System.out.println(request.getParameter("contents"));
		  	System.out.println(request.getParameter("score"));
		  	
		  	NewreviewDTO dto = new NewreviewDTO();
		  	dto.setMembers_id(request.getParameter("members_id"));
		  	dto.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		  	dto.setSubject(request.getParameter("subject"));
		  	dto.setContents(request.getParameter("contents"));
		  	dto.setScore(Integer.parseInt(request.getParameter("score")));
		  	
		  	System.out.println(dto.getMembers_id());
		  	
		    model.addAttribute("dto",dto);
		  	service.insertreview(dto);
	        return review;
	    }	
	  @RequestMapping(value="/review_boardDelete",	 method=RequestMethod.GET)
			public String deletereview(Model model,HttpServletResponse response,String reviewid) throws Exception{
		  int idss =Integer.parseInt(reviewid);
			boolean res1 = service.deletereview(idss);
			System.out.print("????????????:");
			System.out.println(res1);
			if(res1) {
				System.out.println("????????????");
				return "redirect:/review_board";
			} else {
				System.out.println("????????????");
				return "redirect:/review_board";
			}
		}
	  @RequestMapping(value="/Rereview", method = RequestMethod.GET)
		public String reviewUpdate(Model model,HttpSession session, String reviewid) throws Exception{
		  int ids =Integer.parseInt(reviewid);
	      
		  review_boardDTO dto=service.getselectreviewDetail(ids);

		  model.addAttribute("dto",dto);
		  
		  session.setAttribute("dto", dto);
		  return "/Rereview";
	    }

	  @RequestMapping(value="/Rereview_board")
	    public ModelAndView RereviewboardInsert(Model model, HttpSession session,HttpServletRequest request) throws Exception {
		  ModelAndView review = new ModelAndView("redirect:/review_boardDetail?reviewid=" + request.getParameter("id"));
		  	
		  	//-----
		  	session.getAttribute("prodcut_data");
		  	session.getAttribute("loginMember");
		  	// members_id --> ???
		  	// product_id, price, ???????????? --> ??????
		  	// ?????? ?????? ?????? --> ???????????? ???
		  	// ???????????? ??????????????? dto??? ???????????? ???
		  	//members_id prduct_id price ????????????, ?????? ?????? ??????re
		  	System.out.println("??????????????? ?????? ????????? ??????");
		  	System.out.println(request.getParameter("members_id"));
		  	System.out.println(request.getParameter("subject"));
		  	System.out.println(request.getParameter("contents"));
		  	System.out.println(request.getParameter("score"));
		  	
		  	NewreviewDTO dto = new NewreviewDTO();
		  	dto.setId(Integer.parseInt(request.getParameter("id")));
			dto.setMembers_id(request.getParameter("members_id"));
		  	dto.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		  	dto.setSubject(request.getParameter("subject"));
		  	dto.setContents(request.getParameter("contents"));
		  	dto.setScore(Integer.parseInt(request.getParameter("score")));
		  	
		  
		    model.addAttribute("dto",dto);
		  	service.updatereview(dto);
	        return review;
	    }	
	  @RequestMapping(value="/review_comment",method = RequestMethod.POST)
	  public String insertrcomment(int review_id,int members_id,String contents,HttpSession session,HttpServletRequest request)
	  {

		  MembersDTO logindata = (MembersDTO) session.getAttribute("loginMember");
		  boolean result= service.insertcomment(review_id,members_id,contents);
		  return "redirect:/review_boardDetail?reviewid="+review_id;

	  }
}
