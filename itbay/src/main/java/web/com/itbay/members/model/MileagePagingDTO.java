package web.com.itbay.members.model;


public class MileagePagingDTO {
	private int totalPost;
	private int queryNum;
	private int members_id;
	private int numPerPage;
	

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public MileagePagingDTO() {
	}
	
	public MileagePagingDTO(int members_id) {
		this.members_id = members_id;
	}
	public int getTotalPost() {
		return totalPost;
	}
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	public int getQueryNum() {
		return queryNum;
	}
	public void setQueryNum(int queryNum) {
		this.queryNum = queryNum;
	}
	public int getMembers_id() {
		return members_id;
	}
	public void setMembers_id(int members_id) {
		this.members_id = members_id;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	
}
