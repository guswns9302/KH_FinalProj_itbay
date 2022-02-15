package web.com.itbay.review_board.model;

import java.sql.Date;

public class review_boardDTO {
	private int id;
	private int product_id;
	private String members_id;
	private String subject;
	private String contents;
	private int score;
	private int view_CNT;
	private Date order_date;
	private String img_name;
	private int price;
	private String usernickname;
	private String name;

	
	

	
	public review_boardDTO(String subject, String contents,String name) {
		this.setSubject(subject);
		this.setContents(contents);
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public review_boardDTO() {
	
	}
	public String getUsernickname() {
		return usernickname;
	}

	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	
	public int getId() {
		return id;
	}
	public int getProduct_id() {
		return product_id;
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
	public int getView_CNT() {
		return view_CNT;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	public void setView_CNT(int view_CNT) {
		this.view_CNT = view_CNT;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
}
