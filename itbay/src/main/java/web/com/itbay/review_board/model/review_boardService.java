package web.com.itbay.review_board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import web.com.itbay.UploadFileUtils;
import web.com.itbay.history.purchase.model.PurchaseHistoryDTO;
import web.com.itbay.img.model.ImgDTO;
import web.com.itbay.notice_board.model.Notice_boardDTO;
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
public void insertreview(review_boardDTO review_boardDto) throws Exception
	 {
	 dao.insertreview(review_boardDto);
	 }
	
	
	

}
