package web.com.itbay.websocket.chat.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChattingDAO {
	@Autowired
	SqlSession session;

	public int insertMsg(ChattingDTO chattingDTO) {
		int result = this.session.insert("ChattingMapper.insertMsg", chattingDTO);
		return result;
	}

	public List<ChattingDTO> selectChattingData(int roomNum) {
		List<ChattingDTO> chatting_data = this.session.selectList("ChattingMapper.selectChatData", roomNum);
		return chatting_data;
	}

	public List<ChattingRoomNumDTO> selectChatRoom() {
		List<ChattingRoomNumDTO> getChatRoom = this.session.selectList("ChattingMapper.selectChatRoom");
		return getChatRoom;
	}
}
