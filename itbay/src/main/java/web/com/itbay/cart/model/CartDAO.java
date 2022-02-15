package web.com.itbay.cart.model;

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

}
