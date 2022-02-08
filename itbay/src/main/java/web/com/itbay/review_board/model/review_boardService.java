package web.com.itbay.review_board.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class review_boardService {

	@Autowired
	
	review_boardDAO dao;
	

	public review_boardDTO review_board(review_boardDTO inputdata)
	{	
	
		review_boardDTO data=dao.selectreview_board(inputdata);
		return data;
	}
	
}
