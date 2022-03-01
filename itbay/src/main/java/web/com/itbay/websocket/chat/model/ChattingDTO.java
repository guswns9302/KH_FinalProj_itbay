package web.com.itbay.websocket.chat.model;

public class ChattingDTO {
	private int id;
	private int roomnum;
	private int members_id;
	private String members_nickname;
	private String chat_contents;
	
	public ChattingDTO(int roomNum, int members_id,String members_nickname, String send_Msg) {
		this.roomnum = roomNum;
		this.members_id = members_id;
		this.members_nickname = members_nickname;
		this.chat_contents = send_Msg;
	}
	public String getMembers_nickname() {
		return members_nickname;
	}
	public void setMembers_nickname(String members_nickname) {
		this.members_nickname = members_nickname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(int roomnum) {
		this.roomnum = roomnum;
	}
	public int getMembers_id() {
		return members_id;
	}
	public void setMembers_id(int members_id) {
		this.members_id = members_id;
	}
	public String getChat_contents() {
		return chat_contents;
	}
	public void setChat_contents(String chat_contents) {
		this.chat_contents = chat_contents;
	}
}
