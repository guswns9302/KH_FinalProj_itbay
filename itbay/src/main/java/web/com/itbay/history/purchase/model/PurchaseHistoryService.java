package web.com.itbay.history.purchase.model;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHistoryService {
	@Autowired
	PurchaseHistoryDAO dao;
	
	public List<PurchaseHistoryDTO> selectPurchaseHistory(){
		List<PurchaseHistoryDTO> purchaseList = dao.selectPurchaseHistory();
		return purchaseList;
	}
	
	public List<PurchaseHistoryDTO> selectPurchaseNp(){
		List<PurchaseHistoryDTO> listNp = dao.selectPurchaseHistory();
		return listNp;
	}
}