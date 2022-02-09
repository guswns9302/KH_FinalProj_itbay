package web.com.itbay.history.purchase.model;

import java.sql.Date;

public class PurchaseHistoryDTO {

	private int id;
	private int members_id;
	private int product_id;
	private Date create_date;
	
	public PurchaseHistoryDTO() {}
	
	public PurchaseHistoryDTO(int id, int members_id, int product_id, Date create_date) {
		this.id=id;
		this.members_id=members_id;
		this.product_id=product_id;
		this.create_date=create_date;
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
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
}
