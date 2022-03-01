package web.com.itbay.websocket.chat.model;

public class ChattingRoomNumDTO {
	private int roomnum;
	private String members_nickname;
	
	public String getMembers_nickname() {
		return members_nickname;
	}
	public void setMembers_nickname(String members_nickname) {
		this.members_nickname = members_nickname;
	}
	public int getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(int roomnum) {
		this.roomnum = roomnum;
	}
}
