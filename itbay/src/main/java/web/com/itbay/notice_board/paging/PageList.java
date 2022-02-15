package web.com.itbay.notice_board.paging;

import java.util.ArrayList;
import java.util.List;

public class PageList {
	private List<Integer> nums;	//번호만 들어가는 배열
	private int curNum;			//현재 페이지 번호 저장. 나중에 활용
	
	public PageList(int maxPage) {
		this.nums = new ArrayList<Integer>();
		for(int i = 1; i <= maxPage; i++) {
			nums.add(i);
		}
	}
	
	public List<Integer> getNums() { //현재 페이지 목록 반환
		return nums;
	}
	
	public void setNums(List<Integer> nums) {
		this.nums = nums;
	}
	
	public int getCurNum() { //현재 페이지 넘버 반환
		return curNum;
	}
	
	public void setCurNum(int curNum) {
		this.curNum = curNum;
	}
}
