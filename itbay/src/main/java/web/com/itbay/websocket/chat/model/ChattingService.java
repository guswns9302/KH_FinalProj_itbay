package web.com.itbay.websocket.chat.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChattingService {
	
	@Autowired
	ChattingDAO dao;
	
	public boolean insertMsg(ChattingDTO chattingDTO) {
		int result = dao.insertMsg(chattingDTO);
				
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public List<ChattingDTO> getChatData(int roomNum) {
		List<ChattingDTO> chatting_data = dao.selectChattingData(roomNum);
		return chatting_data;
	}

	public List<ChattingRoomNumDTO> getChatRoom() {
		List<ChattingRoomNumDTO> chatRoom = dao.selectChatRoom();
		return chatRoom;
	}

}
