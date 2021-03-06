package web.com.itbay.review_board.model;

import java.sql.Date;

public class review_boardDTO {
	private int id;
	private int product_id;
	private String members_id;
	private String subject;
	private String contents;
	private int score;
	private int view_cnt;
	private Date order_date;
	private Date create_date;
	private String img_name;
	private int price;
	private String usernickname;
	private String name;
	
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	
	public review_boardDTO(String subject,int product_id,int score, String contents,String name,String img_name) {
		this.setSubject(subject);
		this.setProduct_id(product_id);
		this.setContents(contents);
		this.setName(name);
		this.setScore(score);
		this.setImg_name(img_name);
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
	
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getMembers_id() {
		return members_id;
	}
	public void setMembers_id(String members_id) {
		this.members_id = members_id;
	}
}
