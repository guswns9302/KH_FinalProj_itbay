package web.com.itbay.history.sales.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SalesHistoryDAO {

	@Autowired
	SqlSession session;
	
	public List<SalesHistoryDTO> selectSalesHistory(){
		List<SalesHistoryDTO> dtoList = this.session.selectList("SalesHistoryMapper.selectSalesHistory");
		
		return dtoList;
	}
}
