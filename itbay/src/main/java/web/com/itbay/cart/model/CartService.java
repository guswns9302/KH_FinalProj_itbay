package web.com.itbay.cart.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class CartService {

	@Autowired
	CartDAO cartDao;
	
	@Transactional
	public void addCart(List<Integer> idList, int id) {
		for(Integer board_id : idList) {
			CartDTO dto = new CartDTO();
			dto.setMembers_id(id);
			dto.setProduct_id(board_id);
			cartDao.addCart(dto);
		}
	}
	
	public List<CartDTO> selectCart(int members_id){
		
		return cartDao.selectCart(members_id);
	}
	
	public void deleteCart(int id) {
		cartDao.deleteCart(id);
	}

	public List<CartDTO> selectCookieProductBoard(List<Integer> idList) {
		return cartDao.selectCookieProductBoard(idList);
		
	}

}
