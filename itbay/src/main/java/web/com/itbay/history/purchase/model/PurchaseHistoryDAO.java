package web.com.itbay.history.purchase.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseHistoryDAO {

	@Autowired
	SqlSession session;
	
	public PurchaseHistoryDTO selectMembers_id(int members_id) {
		PurchaseHistoryDTO mid= this.session.selectOne("PurchaseHistoryMapper.selectPurchaseHistory",members_id);
		return mid;
	}
	
	public List<PurchaseHistoryDTO> selectPurchaseHistory(int members_id){
		List<PurchaseHistoryDTO> dtoList = this.session.selectList("PurchaseHistoryMapper.selectPurchaseHistory", members_id);
		
		return dtoList;
	}
	
}
