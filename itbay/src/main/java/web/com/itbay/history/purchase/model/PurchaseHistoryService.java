package web.com.itbay.history.purchase.model;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHistoryService {
	@Autowired
	private PurchaseHistoryDAO dao;
	
	public List<PurchaseHistoryDTO> getAllPurchaseHistory(int id){
		PurchaseHistoryDTO dto = new PurchaseHistoryDTO();
		dto.setId(id);
		return dao.selectPurchaseHistory(dto);
	}
}