package web.com.itbay.history.sales.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SalesHistoryService {
	@Autowired
	SalesHistoryDAO dao;
	
	public List<SalesHistoryDTO> selectSalesHistory(){
		List<SalesHistoryDTO> salesList = dao.selectSalesHistory();
		return salesList;
	}
}
