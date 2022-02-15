package web.com.itbay.notice_board.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.com.itbay.members.model.MembersDTO;

@Service
public class Notice_boardService {
	
	@Autowired
	Notice_boardDAO dao;

	public List<Notice_boardDTO> getNotice() {
		List<Notice_boardDTO> noticeList = dao.selectNotice();
		return noticeList;
	}
	
	public List<Notice_boardDTO> selectPage(Map<String, Integer> range) {
		return dao.selectPage(range);
	}

	public int countingNotice() {
		int res = dao.countingNotice();
		return res;
	}
	
	public Notice_boardDTO getNoticeContents(int id) {
		Notice_boardDTO dto = dao.selectContents(id);
		return dto;
	}

	public List<MembersDTO> getMembers() {
		List<MembersDTO> adminList = dao.selectMembers();
		return adminList;
	}

	public int noticeSave(Notice_boardDTO dto) {
		int res = dao.noticeInsert(dto);
		return res;
	}
	
	public int noticeChange(Notice_boardDTO dto) {
		int res = dao.noticeUpdate(dto);
		return res;
	}

	public boolean noticeDelete(int id) {
		boolean res = dao.noticeDelete(id);
		return res;
	}

	
}
