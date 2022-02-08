package web.com.itbay.product.model;

import java.sql.Date;

public class ProductDTO {
	
	private int id;
	
	private String subject;
	
	private String contents;
	
	private int members_id;
	
	private Date create_date;
	
	private int view_CNT;
	
	private int price;
	
	private String sold_out;
	
	private String img_name;

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
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

	public int getMembers_id() {
		return members_id;
	}

	public void setMembers_id(int members_id) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSold_out() {
		return sold_out;
	}

	public void setSold_out(String sold_out) {
		this.sold_out = sold_out;
	}

	
}
