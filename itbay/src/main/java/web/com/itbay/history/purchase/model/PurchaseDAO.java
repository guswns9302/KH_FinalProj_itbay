package web.com.itbay.history.purchase.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseDAO {

	@Autowired
	SqlSession session;

	public PurchaseDTO selectProductInfo(int product_id) {
		PurchaseDTO productInfo = this.session.selectOne("ProductMapper.selectForPurchase",product_id);
		return productInfo;
	}

	public int insertPurchaseHistory(PurchaseHistoryDTO dto) {
		int result = this.session.insert("PurchaseHistoryMapper.insertHistory",dto);
		return result;
	}

	public int insertSalesHistory(PurchaseHistoryDTO dto) {
		int result = this.session.insert("SalesHistoryMapper.insertSalesHistory",dto);
		return result;
	}
	
	public int updateProductSoldOut(int product_id) {
		int result = this.session.update("ProductMapper.updateSoldOut",product_id);
		return result;
	}
}
