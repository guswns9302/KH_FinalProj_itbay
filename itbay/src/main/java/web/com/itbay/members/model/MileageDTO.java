package web.com.itbay.members.model;

import java.sql.Date;

public class MileageDTO {
	private int id;
	private int members_id;
	private int mileage_amount;
	private Date mileage_date;
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
	public int getMileage_amount() {
		return mileage_amount;
	}
	public void setMileage_amount(int mileage_amount) {
		this.mileage_amount = mileage_amount;
	}
	public Date getMileage_date() {
		return mileage_date;
	}
	public void setMileage_date(Date mileage_date) {
		this.mileage_date = mileage_date;
	}
	
	
}