package web.com.itbay.websocket.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import web.com.itbay.members.model.MembersDTO;


public class ChattingHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Map<WebSocketSession, String> userSessions = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 클라이언트와 연결이 되면 동작
		super.afterConnectionEstablished(session);
		sessionList.add(session);
		
		Map<String,Object> map = session.getAttributes();
		MembersDTO logindata = (MembersDTO)map.get("loginMember");
		String userNickname = logindata.getNickname();
		System.out.println(userNickname + " 님이 입장하였습니다.");
		userSessions.put(session, userNickname);
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// 클라이언트에서 메세지를 보내면 동작
		String msg = (String) message.getPayload();
		for(WebSocketSession s: sessionList) {
			TextMessage sendMSG = new TextMessage(userSessions.get(session) + ": " + msg);
			s.sendMessage(sendMSG);
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 클라이언트에서 접속을 종료하면 동작
		super.afterConnectionClosed(session, status);
		sessionList.remove(session);
		System.out.println(userSessions.get(session) + " 접속 종료");
	}
}
