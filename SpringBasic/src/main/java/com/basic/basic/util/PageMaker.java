package com.basic.basic.util;

public class PageMaker {

	private Criteria cri;		//현재 페이지, 한 페이지에 보여줄 행수, 첫번째 행번호에 대한 정보
	private int totalCount;		//전체 데이터의 개수
	
	private int startPage;		//페이징 Nav의 첫번째 페이지 값
	private int endPage;		//페이징 Nav의 마지막 페이지 값
	
	private boolean prev;		//이전 페이지 버튼 활성화 여부
	private boolean next;		//다음 페이지 버튼 활성화 여부
	
	private int navPageNum;		//페이징 Nav에서 보여줄 페이지 개수
	
	public PageMaker(Criteria cri, int totalCount, int navPageNum) {
		setNavPageNum(navPageNum);
		setCri(cri);
		setTotalCount(totalCount);
	}
	
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		endPage = (int)(Math.ceil((double) cri.getCurPage() / navPageNum)) * navPageNum;
		
		startPage = endPage - navPageNum + 1;
		
		if(navPageNum > endPage) startPage = 1;
		
		int totalPages = (int)(Math.ceil(totalCount / (double) cri.getPerPageNum()));
		
		if(endPage > totalPages) endPage = totalPages;
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getNavPageNum() {
		return navPageNum;
	}

	public void setNavPageNum(int navPageNum) {
		this.navPageNum = navPageNum;
	}
	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", navPageNum=" + navPageNum + "]";
	}
	
}
