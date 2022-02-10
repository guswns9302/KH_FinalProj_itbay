package web.com.itbay.history.purchase.model;

import java.sql.Date;

public class PurchaseHistoryDTO {

	private int id;
	private int members_id;
	private int product_id;
	private Date purchase_date;
	private String subject;
	private String img_name;
	private int price;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	
}
