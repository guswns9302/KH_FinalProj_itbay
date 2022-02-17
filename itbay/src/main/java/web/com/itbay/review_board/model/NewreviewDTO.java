package web.com.itbay.review_board.model;

import java.sql.Date;

public class NewreviewDTO {
	
	private int product_id;
	private String members_id;
	private String subject;
	private String contents;
	private int score;
	private Date create_date;
	private int price;
	private int id;
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public String getMembers_id() {
		return members_id;
	}
	public String getSubject() {
		return subject;
	}
	public String getContents() {
		return contents;
	}
	public int getScore() {
		return score;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public int getPrice() {
		return price;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public void setMembers_id(String members_id) {
		this.members_id = members_id;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setCreare_date(Date create_date) {
		this.create_date = create_date;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
	
	
	

