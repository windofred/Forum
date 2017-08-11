package cn.red.model;

import java.util.List;

/**
 * 分页
 * 
 * @author red
 *
 */
public class PageBean<T> {

	private int allPage;// 所有页
	private int curPage;// 当前页

	private List<T> list;// 用于存放T的列表
	
	public PageBean() {}
	
	public PageBean(int allPage, int curPage) {
		this.allPage = allPage;
		this.curPage = curPage;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [allPage=" + allPage + ", curPage=" + curPage
				+ ", list=" + list + "]";
	}

}
