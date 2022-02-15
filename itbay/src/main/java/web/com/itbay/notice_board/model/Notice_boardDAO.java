package web.com.itbay.notice_board.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.com.itbay.members.model.MembersDTO;

@Repository
public class Notice_boardDAO {

	@Autowired
	SqlSession session;
	
	public List<Notice_boardDTO> selectNotice(){
		List<Notice_boardDTO> List = this.session.selectList("NoticeMapper.getNotice");
		return List;
	}

	public int countingNotice() {
		int res = this.session.selectOne("NoticeMapper.countingNotice");
		return res;
	}
	
	public List<Notice_boardDTO> selectPage(Map<String, Integer> range) {
		List<Notice_boardDTO> List = this.session.selectList("NoticeMapper.selectPage", range);
		return List;
	}
	
	public Notice_boardDTO selectContents(int id) {
		Notice_boardDTO dto = this.session.selectOne("NoticeMapper.getContents", id);
		return dto;
	}

	public List<MembersDTO> selectMembers() {
		List<MembersDTO> List = this.session.selectList("NoticeMapper.getMembers");
		return List;
	}

	public int noticeInsert(Notice_boardDTO dto) {
		int res = this.session.insert("NoticeMapper.insertNotice", dto);
		return res;
	}

	public boolean noticeDelete(int id) {
		int res = this.session.delete("NoticeMapper.deleteNotice", id);
		return res == 1 ? true : false;
	}
}
