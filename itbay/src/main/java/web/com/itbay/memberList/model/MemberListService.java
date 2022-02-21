package web.com.itbay.memberList.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.com.itbay.history.purchase.model.PurchaseHistoryDTO;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.notice_board.model.Notice_boardDTO;

@Service
public class MemberListService {
	
	@Autowired
	MemberListDAO dao;
	
	public int countingMember() {
		int res = dao.countingMember();
		return res;
	}
	
	public List<MembersDTO> selectMember(Map<String, Integer> range) {
		return dao.selectMember(range);
	}

	public int countingHistories() {
		int res = dao.countingHistories();
		return res;
	}
	public List<PurchaseHistoryDTO> selectHistories(Map<String, Integer> range) {
		List<PurchaseHistoryDTO> histories = dao.selectHistories(range);
		return histories;
	}
}
