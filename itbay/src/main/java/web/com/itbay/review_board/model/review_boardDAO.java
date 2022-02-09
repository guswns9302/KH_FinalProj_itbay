package web.com.itbay.review_board.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class review_boardDAO {

	
	@Autowired
	SqlSession session;

	public review_boardDTO selectreview_board(review_boardDTO inputdata) {
		
		
		review_boardDTO data = this.session.selectOne("review_boardMapper.selectreview_board", inputdata);
		return data;
	}
	
}
