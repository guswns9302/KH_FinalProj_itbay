package web.com.itbay.review_board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.com.itbay.product.model.ProductDTO;

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
public void insertreview(NewreviewDTO NewreviewDTO) throws Exception
	 {
	 dao.insertreview(NewreviewDTO);
	 }

public review_boardDTO  getreviewinsert(int ids) {
	
	review_boardDTO dto= dao.reviewinsert(ids);
	
	return dto;
	
}

public ProductDTO getproductdata(int ids) {
	ProductDTO dto= dao.productdata(ids);
	return dto;
}
public boolean deletereview(int idss) {
	int res2 = dao.deletcomment(idss);
	boolean res1 = dao.deletereview(idss);
	
	return res1;
}

public void reviewupdate(review_boardDTO review_boardDto) {
	
	dao.reviewupdate(review_boardDto);
}
public void updatereview(NewreviewDTO NewreviewDTO) throws Exception
{
dao.updatereview(NewreviewDTO);
}
public List<commentDTO> getComment(int id)
{
	List<commentDTO> datas=dao.selectcomment(id);
	return datas;
}

public boolean insertcomment(int review_id, int members_id, String contents) {
	commentDTO data=new commentDTO();
	data.setReview_id(review_id);
	data.setMembers_id(members_id);
	data.setContents(contents);
	
	int result = dao.insertcomment(data);
	if(result==1) {
		return true;
	}
	return false;
}

public boolean inviewcnt(int id) {
	int result=dao.updateviewcnt(id);
	if(result ==1) {
		return true;
}
	return false;
}


}

