package web.com.itbay.notice_board.model;

import java.util.List;

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

	public boolean noticeDelete(int id) {
		boolean res = dao.noticeDelete(id);
		return res;
	}

	
}
