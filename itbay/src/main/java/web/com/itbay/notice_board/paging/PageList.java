package web.com.itbay.notice_board.paging;

import java.util.ArrayList;
import java.util.List;

public class PageList {
	private List<Integer> nums;	//번호만 들어가는 배열
	private int curNum;			//현재 페이지 번호 저장. 나중에 활용
	private int maxNum;			//max페이지 필요.
	
	public PageList() {}
	
	public PageList(int curPage, int maxPage, int pageListCnt) {
		this.nums = new ArrayList<Integer>();
		this.curNum = curPage;
		this.maxNum = maxPage;
		int startPage = (this.curNum/pageListCnt) * 10 + 1;
		
		for(int i = 0; i < 10; i++) {
			if(startPage+i <= maxPage) {
				nums.add(startPage + i);
			} else {
				break;
			}
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

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	
}
