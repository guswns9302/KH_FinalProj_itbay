package web.com.itbay.history.purchase.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseHistoryDAO {

	@Autowired
	SqlSession session;
	
	public List<PurchaseHistoryDTO> selectPurchaseHistory(){
		List<PurchaseHistoryDTO> dtoList = this.session.selectList("PurchaseHistoryMapper.selectPurchaseHistory");
		
		return dtoList;
	}
	
}
