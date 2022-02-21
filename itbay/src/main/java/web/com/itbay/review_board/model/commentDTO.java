package web.com.itbay.review_board.model;

import java.sql.Date;

public class commentDTO {
private int id;
private int review_id;
private int members_id;
private String nickname;
private String contents;
private Date create_date;
private int memberid;

public int getMemberid() {
	return memberid;
}

public void setMemberid(int memberid) {
	this.memberid = memberid;
}

public String getContents() {
	return contents;
}

public int getMembers_id() {
	return members_id;
}

public void setMembers_id(int members_id) {
	this.members_id = members_id;
}

public void setContents(String contents) {
	this.contents = contents;
}

public int getId() {
	return id;
}

public String getNickname() {
	return nickname;
}

public Date getCreate_date() {
	return create_date;
}
public void setId(int id) {
	this.id = id;
}

public int getReview_id() {
	return review_id;
}

public void setReview_id(int review_id) {
	this.review_id = review_id;
}

public void setNickname(String nickname) {
	this.nickname = nickname;
}

public void setCreate_date(Date create_date) {
	this.create_date = create_date;
}
}
