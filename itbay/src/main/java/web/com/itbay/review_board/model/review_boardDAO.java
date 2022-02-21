package web.com.itbay.review_board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.com.itbay.product.model.ProductDTO;

@Repository
public class review_boardDAO {

	
	@Autowired
	SqlSession session;

	public List<review_boardDTO> select(){
		List<review_boardDTO> List = this.session.selectList("review_boardMapper.selectReview");
		return List;
	}
	
 
 public void insertreview(NewreviewDTO NewreviewDTO)  throws Exception
 {
  session.insert("review_boardMapper.insertReview",NewreviewDTO);
 }

 public review_boardDTO selectreviewDetail(int id) {
		review_boardDTO dto = this.session.selectOne("review_boardMapper.getreviewDetail",id);
		
		return dto;
	}


 public  review_boardDTO reviewinsert(int ids) {
		review_boardDTO dto = this.session.selectOne("review_boardMapper.reviewinsert",ids);
		
		return dto;
	}


public ProductDTO productdata(int ids) {
	ProductDTO dto = this.session.selectOne("review_boardMapper.productdata",ids);
	
	return dto;	
}

public boolean deletereview(int idss) {
	System.out.println("dao에서 삭제");
	System.out.println(idss);
	int res = this.session.delete("review_boardMapper.deletereview", idss); 
	return res == 1 ? true : false;
}
public void reviewupdate(review_boardDTO review_boardDto) {
	this.session.update("review_boardMapper.updatereview", review_boardDto);
	
}
public void updatereview(NewreviewDTO NewreviewDTO)  throws Exception
{
 session.update("review_boardMapper.updatereview",NewreviewDTO);
}
public List<commentDTO>  selectcomment(int id){
return session.selectList("review_boardMapper.selectcomment",id);
}


public int insertcomment(commentDTO data) {

	return session.insert("review_boardMapper.insertcomment",data);
}


public int updateviewcnt(int id) {
	
	return session.update("review_boardMapper.viewcnt",id);
}


public int deletcomment(int idss) {
	
	return session.delete("review_boardMapper.deletecomment",idss);
}
}