package web.com.itbay.notice_board.paging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pagination<T> {
	private int pageListCnt;	// 한 페이지에 출력될 게시물 수
	private int maxCnt;			// 전체 게시물 수
	private int maxPage;		// 전체 페이지 수
	private int curPage;		// 현재 페이지 번호.
	private List<T> dataList;
	private PageList pageList;
	
	/*
	List<Account DTO>, List<BoardDTO>, List<CommentDTO> 등 다양한 객체를 담을 수 있는데, 그 때마다
	매번 페이지 처리를 위한 객체를 둘 수 업기 때문에 T를 사용
	(Generic)타입 - 어떤 객체든 대응이 될 수 있게. 타입들을 일반화 시켜줌.
	ex) 
	Pagination<BoardDTO> bpaging = ~~~ 로 지정되어있으면
	=>클래스 내부에서 T는 BoardDTO,  
	*/
	
	public Pagination(int maxCnt, int pageListCnt) {
		this.pageListCnt = pageListCnt;
		this.maxCnt = maxCnt;
		if(this.maxCnt != 0) {
			this.maxPage = this.maxCnt / this.pageListCnt;
			if(this.maxCnt % this.pageListCnt > 0) {
				this.maxPage+=1;
			}
		}else { 
			this.maxPage = 1;
		}
		this.curPage = 1;
	}

	public Map<String, Integer> getPage(int page) throws Exception{
		if(page == 0) {
			throw new Exception("페이지 번호는 0이 될 수 없습니다.");
		}
		if(page > this.maxPage) {
			throw new Exception("최대 페이지를 초과했습니다..");
		}
		this.curPage = page;
		int startList = (this.curPage - 1) * this.pageListCnt + 1;
		int endList = this.curPage*pageListCnt;
		if(endList > this.maxCnt) {
			endList = this.maxCnt;
		}
		Map<String, Integer> range = new HashMap<String, Integer>();
		range.put("start", startList);
		System.out.println("Start :"+ startList);
		range.put("end", endList);
		System.out.println("end :"+ endList);
		range.put("listCnt", pageListCnt);
		
		return range;
	}

	public PageList getPageList() {
		System.out.println("curPage :" + this.curPage);
//		pageList = new PageList();
//		pageList.setCurNum(curPage);
		pageList = new PageList(this.curPage, this.maxPage, this.pageListCnt);
		return pageList;
	}
	
	public Pagination(List<T> dataList, int pageListCnt) {
		this.pageListCnt = pageListCnt;
		this.dataList = dataList;
		if(dataList.size() != 0) {
			this.maxPage = dataList.size() / this.pageListCnt;
			if(dataList.size() % this.pageListCnt > 0) {
				this.maxPage++;
			}
		} else {
			this.maxPage = 0;
		}
		this.curPage = 1;
	}

	public int getPageListCnt() {
		return pageListCnt;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getCurPage() {
		return curPage;
	}

	
	public List<T> getDataList() {
		return dataList;
	}

}
