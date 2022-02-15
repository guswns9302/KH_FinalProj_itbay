package web.com.itbay.review_board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class review_boardService {

	@Autowired
	
	review_boardDAO dao;
	
	public List<review_boardDTO> selectReview() {
		List<review_boardDTO> List = dao.select();
		return List;
	}
	
public review_boardDTO getselectreviewDetail(int id) {
		
		review_boardDTO dto = dao.selectreviewDetail(id);
		
		return dto;
		
	}
public void insertreview(review_boardDTO review_boardDto) throws Exception
	 {
	 dao.insertreview(review_boardDto);
	 }
	
	
	

}
