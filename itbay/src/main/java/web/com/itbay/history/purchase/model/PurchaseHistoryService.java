package web.com.itbay.history.purchase.model;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHistoryService {
	@Autowired
	private PurchaseHistoryDAO dao;
	
	public List<PurchaseHistoryDTO> getAllPurchaseHistory(){
		return dao.selectPurchaseHistory();
	}
}