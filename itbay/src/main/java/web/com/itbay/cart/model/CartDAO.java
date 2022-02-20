package web.com.itbay.cart.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAO {

	
	@Autowired
	SqlSession session;	
	
	public void addCart(CartDTO dto) {
		this.session.insert("CartMapper.saveCart", dto);
	}
	
	public List<CartDTO> selectCart(int members_id){
		List<CartDTO> cartList = this.session.selectList("CartMapper.selectCart", members_id);
		
		return cartList;
	}
	
	public void deleteCart(int id) {
		this.session.delete("CartMapper.deleteCart", id);
	}

	public List<CartDTO> selectCookieProductBoard(List<Integer> idList) {
		return this.session.selectList("CartMapper.selectCookieProductBoard", idList);
	}
	
	public int selectMemberCart(CartDTO cartDto) {
		return this.session.selectOne("CartMapper.countMemberCart", cartDto);
		
	}

}
