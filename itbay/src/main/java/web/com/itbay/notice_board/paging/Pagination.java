package web.com.itbay.notice_board.paging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pagination<T> {
	private int listCnt;	// 한 페이지에 출력될 게시물 수
	private int maxCnt;
	private int maxPage;	// 한 화면에 출력될 페이지 수(아래 바)
	private int curPage;	// 현재 페이지 번호.
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
		this.listCnt = pageListCnt;
		this.maxCnt = maxCnt;
		this.maxPage = this.maxCnt / this.listCnt;
		if(this.maxCnt != 0) {
			this.maxPage = this.maxCnt / this.listCnt;
			if(this.maxCnt % this.listCnt > 0) {
				this.maxPage+=1;
			}
		}else { 
			this.maxPage = 1;
		}
		this.curPage = 1;
	}

	public Pagination(List<T> dataList, int listCnt) {
		this.listCnt = listCnt;
		this.dataList = dataList;
		if(dataList.size() != 0) {
			this.maxPage = dataList.size() / this.listCnt;
			if(dataList.size() % this.listCnt > 0) {
				this.maxPage++;
			}
		} else {
			this.maxPage = 0;
		}
		this.curPage = 1;
	}

	public int getListCnt() {
		return listCnt;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public Map<String, Integer> getPage(int page) throws Exception{
		if(page == 0) {
			throw new Exception("페이지 번호는 0이 될 수 없습니다.");
		}
		if(page > this.maxPage) {
			throw new Exception("최대 페이지를 초과했습니다..");
		}
		this.curPage = page;
		int startPage = (this.curPage - 1) * this.listCnt + 1;
		int endPage = this.curPage*listCnt;
		if(endPage > this.maxCnt) {
			endPage = this.maxCnt;
		}
		Map<String, Integer> range = new HashMap<String, Integer>();
		range.put("start", startPage);
		System.out.println("Start :"+startPage);
		range.put("end", endPage);
		System.out.println("end :"+endPage);
		range.put("listCnt", listCnt);
		System.out.println("end :"+listCnt);
		
		return range;
	}
	
	public List<T> getDataList() {
		return dataList;
	}

	public PageList getPageList() {
		pageList = new PageList(this.maxPage);
		pageList.setCurNum(this.curPage);
		return pageList;
	}
}
