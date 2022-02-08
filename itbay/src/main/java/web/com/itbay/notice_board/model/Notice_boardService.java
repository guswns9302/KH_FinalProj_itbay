package web.com.itbay.notice_board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Notice_boardService {
	
	@Autowired
	Notice_boardDAO dao;

	public List<Notice_boardDTO> getNotice() {
		List<Notice_boardDTO> List = dao.select();
		return List;
	}
	
}
