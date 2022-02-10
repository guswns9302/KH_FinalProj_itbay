package web.com.itbay.notice_board.model;

import java.sql.Date;

public class Notice_boardDTO {
	private int id;
	private String subject;
	private String contents;
	private String members_id;
	private Date create_date;
	private int view_CNT;
	
	public Notice_boardDTO() {}
	
	public Notice_boardDTO(String subject, String contents) {
		this.setSubject(subject);
		this.setContents(contents);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getMembers_id() {
		return members_id;
	}
	public void setMembers_id(String members_id) {
		this.members_id = members_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public int getView_CNT() {
		return view_CNT;
	}
	public void setView_CNT(int view_CNT) {
		this.view_CNT = view_CNT;
	}
	
	
}
