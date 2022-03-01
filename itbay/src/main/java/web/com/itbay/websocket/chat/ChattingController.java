package web.com.itbay.websocket.chat;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.websocket.chat.model.ChattingDTO;
import web.com.itbay.websocket.chat.model.ChattingRoomNumDTO;
import web.com.itbay.websocket.chat.model.ChattingService;

@Controller
public class ChattingController {

	@Autowired
	ChattingService service;
	
	// 웹소켓 연결되었을 떄, 채팅 기록을 가져옴
	@RequestMapping(value = "/liveChat", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String chatting(HttpSession session, int roomNum) {
		List<ChattingDTO> chatting_data = service.getChatData(roomNum);
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		for(int i = 0; i < chatting_data.size(); i++) {
			JSONObject list_json = new JSONObject();
			list_json.put("ID", chatting_data.get(i).getId());
			list_json.put("ROOMNUM", chatting_data.get(i).getRoomnum());
			list_json.put("MEMBERS_ID", chatting_data.get(i).getMembers_id());
			list_json.put("CHAT_CONTENTS", chatting_data.get(i).getChat_contents());
			list_json.put("MEMBERS_NICKNAME", chatting_data.get(i).getMembers_nickname());
			jarr.add(list_json);
		}
		if (chatting_data != null) {
			json.put("status", "success");
			json.put("chatting_data", jarr);
		} else {
			json.put("status", "fail");
			json.put("message", "패스워드 변경을 실패했습니다.");
		}
		return json.toJSONString();
	}
	
	// 채팅방 목록 불러오기
	@RequestMapping(value = "/getChatroom", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String getChatroom() {
		List<ChattingRoomNumDTO> chatRoom = service.getChatRoom();
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		for(int i = 0; i < chatRoom.size(); i++) {
			JSONObject list_json = new JSONObject();
			list_json.put("ROOMNUM", chatRoom.get(i).getRoomnum());
			list_json.put("MEMBERS_NICKNAME", chatRoom.get(i).getMembers_nickname());
			jarr.add(list_json);
		}
		if (chatRoom != null) {
			json.put("status", "success");
			json.put("roomList", jarr);
		} else {
			json.put("status", "fail");
		}
		return json.toJSONString();
	}
	
	// 채팅 메세지를 보낼 때, db에 채팅 내용을 저장함
	@RequestMapping(value = "/insertMsg", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String insertMsg(HttpSession session,int roomNum, String send_Msg) {
		MembersDTO logindata = (MembersDTO) session.getAttribute("loginMember");
		ChattingDTO chattingDTO = new ChattingDTO(roomNum, logindata.getId(), logindata.getNickname(), send_Msg);
		
		boolean result = service.insertMsg(chattingDTO);
		
		JSONObject json = new JSONObject();
		if (result) {
			json.put("status", "success");
			json.put("message", "메세지 저장 성공");
		} else {
			json.put("status", "fail");
			json.put("message", "메세지 저장 실패");
		}
		return json.toJSONString();
	}
	
}
