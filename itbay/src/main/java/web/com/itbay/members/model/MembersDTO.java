package web.com.itbay.members.model;

import java.sql.Date;

public class MembersDTO {
	private int id;
	private String nickname;
	private String pw;
	private String username;
	private int age;
	private Date birth;
	private String phone;
	private String address;
	private String email_address;
	private Date joinDate;
	private Date loginDate;
	private char social_login;
	private char master;
	
	private String img_name;
	
	
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	
	public MembersDTO(String nickname, String pw) {
		this.nickname = nickname;
		this.pw = pw;
	}
	public MembersDTO() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = (Date) birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public char getSocial_login() {
		return social_login;
	}
	public void setSocial_login(char social_login) {
		this.social_login = social_login;
	}
	public char getMaster() {
		return master;
	}
	public void setMaster(char master) {
		this.master = master;
	}
}
