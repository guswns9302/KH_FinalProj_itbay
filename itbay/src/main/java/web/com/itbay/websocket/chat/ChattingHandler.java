package web.com.itbay.websocket.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import web.com.itbay.members.model.MembersDTO;


public class ChattingHandler extends TextWebSocketHandler {
	
	// 로그인 한 전체 연결
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	// 1:1 연결
	private Map<String, WebSocketSession> userSessions = new HashMap<>();
	// 채팅 연결 시 채팅방 번호, 유저 저장
	private Map<String, Integer> chatRoom_num = new HashMap<String, Integer>();
	
	// 웹소켓에서 httpsession 정보를 활용 => httpsession에서 로그인한 사용자의 nickname정보를 가져옴
	private String getSender(WebSocketSession session) {
		Map<String,Object> httpsessionData = session.getAttributes();
		MembersDTO logindata = (MembersDTO)httpsessionData.get("loginMember");
		
		if(logindata == null) {
			return session.getId();
		}
		else {
			return logindata.getNickname();
		}
	}
	
	// 서버에 접속 성공 했을 때
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		String sender = getSender(session);
		userSessions.put(sender, session);
	}
	
	// 소켓이 메세지를 보낼 때
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		//String sender = getSender(session);
		// 브로드 캐스팅 -> 모든 유저에게 메세지를 보냄
		String msg = (String) message.getPayload();
		for(WebSocketSession s: sessionList) {
			TextMessage sendMSG = new TextMessage(msg);
			s.sendMessage(sendMSG);
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 클라이언트에서 접속을 종료하면 동작
		super.afterConnectionClosed(session, status);
		sessionList.remove(session);
		userSessions.remove(session.getId());
	}
}
