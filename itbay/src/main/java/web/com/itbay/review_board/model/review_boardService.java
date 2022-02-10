package web.com.itbay.review_board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.com.itbay.notice_board.model.Notice_boardDTO;

@Service
public class review_boardService {

	@Autowired
	
	review_boardDAO dao;
	
	public List<review_boardDTO> selectReview() {
		List<review_boardDTO> List = dao.select();
		return List;
	}

}
