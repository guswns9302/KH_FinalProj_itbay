package web.com.itbay.review_board.model;

import org.springframework.beans.factory.annotation.Autowired;

import web.com.itbay.members.model.MembersDAO;
import web.com.itbay.members.model.MembersDTO;

public class review_boardService {

	@Autowired
	review_boardDAO dao;
	
	

	public review_boardDTO review_board(review_boardDTO inputdata)
	{
		
		System.out.println("dao에서 출력 : " + inputdata.getSubject());
		System.out.println("dao에서 출력 : " + inputdata.getContents());
		System.out.println("dao에서 출력 : " + inputdata.getScore());
		review_boardDTO data=dao.selectreview_board(inputdata);
		return data;
	}
	
}
