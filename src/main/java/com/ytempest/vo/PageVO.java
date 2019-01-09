package com.ytempest.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageVO<T> implements Serializable {

	private static final long serialVersionUID = 8298224259707862105L;

	private List<T> list;
	/*
	 * 记录的总条数
	 */
	private Long total;
	/*
	 * 每一页的记录数量
	 */
	private Integer pageSize;
	/*
	 * 当前页面
	 */
	private Integer currentPage;
	/*
	 * 页面的总数
	 */
	private Integer pageCount;

	public PageVO() {
		super();
	}

	public PageVO(Long total, Integer pageSize, Integer currentPage,
			Integer pageCount) {
		super();
		this.total = total;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setpageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 获取当前页面的前len个和后len个页面
	 * 
	 * @param len
	 * @return
	 */
	public List<Integer> getPages(int len) {
		List<Integer> pageList = new ArrayList<Integer>(len * 2 + 1);
		if (pageCount > 0) {
			int start = currentPage - len <= 0 ? 1 : currentPage - len;
			int end = (int) (currentPage + len > pageCount
					? pageCount
					: currentPage + len);
			for (int i = start; i <= end; i++) {
				pageList.add(i);
			}
		}

		return pageList;

	}

	@Override
	public String toString() {
		return "PageVO [total=" + total + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", pageCount=" + pageCount
				+ "]";
	}

}
