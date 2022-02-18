package web.com.itbay.cart.model;

import java.sql.Date;

public class CartDTO {

	private int id;
	
	private int members_id;
	
	private int product_id;
	
	private Date add_date;
	
	private String order_status;
	
	private String subject;
	
	private int price;
	
	private String img_name;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public void setId(int id) {
		this.id = id;
	}

	public int getMembers_id() {
		return members_id;
	}

	public void setMembers_id(int members_id) {
		this.members_id = members_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	
}
