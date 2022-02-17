package web.com.itbay.history.purchase.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
	@Autowired
	PurchaseDAO dao;

	public PurchaseDTO getProductInfo(int product_id) {
		PurchaseDTO productInfo = dao.selectProductInfo(product_id);
		return productInfo;
	}

	public boolean insertPurchaseHistory(PurchaseHistoryDTO dto) {
		int result = dao.insertPurchaseHistory(dto);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean insertsalesHistory(PurchaseHistoryDTO dto) {
		int result = dao.insertSalesHistory(dto);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean updateProductSoldOut(int product_id) {
		int result = dao.updateProductSoldOut(product_id);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	
}