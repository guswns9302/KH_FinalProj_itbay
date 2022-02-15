package web.com.itbay.history.purchase.model;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHistoryService {
	@Autowired
	PurchaseHistoryDAO dao;
	
//	public PurchaseHistoryDTO getMembers_id(int members_id) {
//		PurchaseHistoryDTO getMid = dao.selectMembers_id(members_id);
//		return getMid;
//	}
	
	public List<PurchaseHistoryDTO> selectPurchaseHistory(int members_id){
		List<PurchaseHistoryDTO> purchaseList = dao.selectPurchaseHistory(members_id);
		return purchaseList;
	}
	
	public List<PurchaseHistoryDTO> selectPurchaseNp(int members_id){
		List<PurchaseHistoryDTO> listNp = dao.selectPurchaseHistory(members_id);
		return listNp;
	}
}