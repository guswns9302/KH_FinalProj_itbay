package web.com.itbay.review_board.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.com.itbay.members.model.MembersDTO;



@Repository
public class review_boardDAO {

	
	@Autowired
	SqlSession session;

	public review_boardDTO selectreview_board(review_boardDTO inputdata) {
		System.out.println("dao에서 출력 : " + inputdata.getSubject());
		System.out.println("dao에서 출력 : " + inputdata.getContents());
		System.out.println("dao에서 출력 : " + inputdata.getScore());
		
		review_boardDTO data = this.session.selectOne("review_boardMapper.selectreview_board", inputdata);
		return data;
	}
	
}
