package com.atguigu.atcrowdfunding.common;

import java.util.List;

/**
 * 分页实体类
 * @author 18801
 *
 */
public class Page<T> {

	private List<T> datas;
	
	private int totalno;
	private int pageno;
	private int totalsize;
	private int pagesize;
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public int getTotalno() {
		return totalno;
	}
	public void setTotalno(int totalno) {
		this.totalno = totalno;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getTotalsize() {
		return totalsize;
	}
	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	
}
