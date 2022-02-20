package web.com.itbay.memberList.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.com.itbay.history.purchase.model.PurchaseHistoryDTO;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.notice_board.model.Notice_boardDTO;

@Repository
public class MemberListDAO {

	@Autowired
	SqlSession session;

	public int countingMember() {
		int res = this.session.selectOne("MemberListMapper.countingMember");
		return res;
	}

	public List<MembersDTO> selectMember(Map<String, Integer> range) {
		List<MembersDTO> List = this.session.selectList("MemberListMapper.selectMember", range);
		return List;
	}

	public int countingHistories() {
		int res = this.session.selectOne("MemberListMapper.countingHistories");
		return res;
	}
	
	public List<PurchaseHistoryDTO> selectHistories(Map<String, Integer> range) {
		List<PurchaseHistoryDTO> histories = this.session.selectList("MemberListMapper.selectHistories", range);
		return histories;
	}
}
