package web.com.itbay.notice_board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Notice_boardDAO {

	@Autowired
	SqlSession session;
	
	public List<Notice_boardDTO> select(){
		List<Notice_boardDTO> List = this.session.selectList("NoticeMapper.getNotice");
		return List;
	}
	
}
