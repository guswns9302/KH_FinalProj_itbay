package web.com.itbay.websocket.chat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.websocket.chat.model.ChattingDTO;
import web.com.itbay.websocket.chat.model.ChattingService;

@Controller
public class ChattingController {

	@Autowired
	ChattingService service;
	
	// 웹소켓 연결되었을 떄, 채팅 기록을 가져옴
	@RequestMapping(value = "/liveChat", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String chatting(HttpSession session, int members_id) {
		System.out.println("채팅 연결 컨트롤러 실행");
		int roomNum = members_id;
		List<ChattingDTO> chatting_data = service.getChatData(roomNum);
		
		for(int i = 0; i < chatting_data.size(); i++) {
			System.out.println(chatting_data.get(i).getChat_contents());
		}
		
		JSONObject json = new JSONObject();
		if (chatting_data != null) {
			System.out.println("if문 실행");
			json.put("status", "success");
			json.put("chatting_data", chatting_data.get(0).getChat_contents());
		} else {
			json.put("status", "fail");
			json.put("message", "패스워드 변경을 실패했습니다.");
		}
		return json.toJSONString();
	}
	
	// 채팅 메세지를 보낼 때, db에 채팅 내용을 저장함
	@RequestMapping(value = "/insertMsg", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String insertMsg(int members_id, String send_Msg) {
		int roomNum = members_id;
		ChattingDTO chattingDTO = new ChattingDTO(roomNum, members_id, send_Msg);
		
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
