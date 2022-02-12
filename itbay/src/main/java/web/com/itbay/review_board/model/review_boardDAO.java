package web.com.itbay.review_board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.com.itbay.notice_board.model.Notice_boardDTO;

@Repository
public class review_boardDAO {

	
	@Autowired
	SqlSession session;

	public List<review_boardDTO> select(){
		List<review_boardDTO> List = this.session.selectList("review_boardMapper.selectReview");
		return List;
	}
	
 
 public void insertreview(review_boardDTO review_boardDto)  throws Exception
 {
  session.insert("review_boardMapper.insertReview",review_boardDto);
 }
}
