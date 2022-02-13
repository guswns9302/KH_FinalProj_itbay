package web.com.itbay;

public class PagingUtil {

	
	/** 현재 페이지 번호. */
	private int curPageNo = 1;

	/** 페이지당 리스트 수. */
	private int rowCnt = 9;

	/** 보여줄 페이징 수. */
	private int pageSize = 5;

	/** 총 건 수. */
	private int totalCnt;

	/** 총 페이지 수. */
	private int totalPageCnt;

	/** 첫번째 페이지 번호. */
	private int firstPageNo;

	/** 마지막 페이지 번호. */
	private int lastPageNo;

	/** The start index. */
	private int startIndex;

	/** The end index. */
	private int endIndex;


	/**
	 * 생성자
	 */
	public PagingUtil() { }
	
	/**
	 * 생성자
	 * 
	 * @param totalCnt 총건수
	 * @param curPageNo 현재 페이지 번호
	 */
	public PagingUtil(int totalCnt, int curPageNo) {
		if(curPageNo == 0) {
			curPageNo = 1;
		}
		this.curPageNo = curPageNo;
		this.totalCnt = totalCnt;
	}
	
	/**
	 * 생성자
	 * 
	 * @param totalCnt 총건수
	 * @param curPageNo 현재 페이지 번호
	 * @param rowCnt 리스트 수
	 */
	public PagingUtil(int totalCnt, int curPageNo, int rowCnt) {
		if(curPageNo == 0) {
			curPageNo = 1;
		}
		this.totalCnt = totalCnt;
		this.curPageNo = curPageNo;
		this.rowCnt = rowCnt;
	}

	/**
	 * 생성자
	 * 
	 * @param totalCnt 총건수
	 * @param curPageNo 현재 페이지 번호
	 * @param rowCnt 리스트 수
	 * @param pageSize 페이지 사이즈
	 */
	public PagingUtil(int totalCnt, int curPageNo, int rowCnt, int pageSize) {
		if(curPageNo == 0) {
			curPageNo = 1;
		}
		this.totalCnt = totalCnt;
		this.curPageNo = curPageNo;
		this.rowCnt = rowCnt;
		this.pageSize = pageSize;

	}

	public int getTotalPageCnt() {
		this.totalPageCnt = ((getTotalCnt() - 1) / getRowCnt()) + 1;
		return this.totalPageCnt;
	}

	public int getFirstPageNo() {
		this.firstPageNo = ((getCurPageNo() - 1) / getPageSize() * getPageSize() + 1);
		return this.firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getLastPageNo() {
		this.lastPageNo = getFirstPageNo() + getPageSize() - 1;
		if (this.lastPageNo > getTotalPageCnt())
			this.lastPageNo = getTotalPageCnt();
		return this.lastPageNo;
	}

	public int getStartIndex() {
		
		if(getCurPageNo() == 0 || getCurPageNo() == 1){
			this.startIndex = (getCurPageNo() - 1) * getRowCnt();
		} else {
			this.startIndex = ((getCurPageNo() - 1) * getRowCnt()+1);
		}
		return startIndex;
	}

	public int getEndIndex() {
		this.endIndex = getCurPageNo() * getRowCnt();
		return endIndex;
	}

	public String mkPageing() {
		
		int numPageGroup = (int) Math.ceil((double) curPageNo / pageSize);
		int pageGroupCnt = totalCnt / (rowCnt * pageSize) + (totalCnt % (rowCnt * pageSize) == 0 ? 0 : 1);
		
		String html = "<ul class='pagination' style='justify-content: center;'>";
		
		if(totalCnt > 0) {
			
			if(numPageGroup > 1) {
				html += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='go_page(\""+(((numPageGroup-2)*pageSize)+1)+"\")'>이전</a></li>";
			}
			
			for(int i=getFirstPageNo(); i <= getLastPageNo(); i++) {

				if(curPageNo == i) {
					html += "<li class='page-item active'>";
				}
				if(curPageNo != i) {
					html += "<li class='page-item'>";
				}
				html +=   "<a class='page-link' href='javascript:void(0);' onclick='go_page(\""+i+"\")'>";
				html +=    i;
				html +=   "</a>";
				html += "</li>";
			}

			if(numPageGroup < pageGroupCnt) {
				html += "<li class='page-item'><a class='page-link' href='javascript:void(0);' onclick='go_page(\""+((numPageGroup*pageSize)+1)+"\")'>다음</a></li>";
			}
		}
		
		html += "</ul>";
		return html;
	}

	public int getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(int curPageNo) {
		this.curPageNo = curPageNo;
	}

	public int getRowCnt() {
		return this.rowCnt;
	}

	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	
	
	
	

	
}
