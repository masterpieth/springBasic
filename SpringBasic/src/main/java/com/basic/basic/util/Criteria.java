package com.basic.basic.util;

public class Criteria {

	private int curPage;	//현재 페이지
	private int perPageNum; //페이지당 보여줄 게시글 개수
	private int startRows;  //query 시작 인덱스
	
	public Criteria() {
		this.curPage = 1;
		this.perPageNum = 10;
		setStartRows();
	}
	
	public Criteria(int curPage, int perPageNum) {
		setCurPage(curPage);
		setPerPageNum(perPageNum);
		setStartRows();
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		if(curPage <= 0) {
			this.curPage = 1;
			return;
		}
		this.curPage = curPage;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}

	public void setStartRows() {
		this.startRows = (this.curPage - 1) * this.perPageNum;
	}

	public int getStartRows() {
		return startRows;
	}
	
	@Override
	public String toString() {
		return "Criteria [curPage=" + curPage + ", perPageNum=" + perPageNum + ", startRows=" + startRows + "]";
	}
	
	
}
