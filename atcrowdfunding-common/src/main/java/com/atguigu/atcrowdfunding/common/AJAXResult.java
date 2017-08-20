package com.atguigu.atcrowdfunding.common;

public class AJAXResult {

	private Page pageObj;
	private boolean success;
	private String errorMsg;
	private Object datas;
	
	public Object getDatas() {
		return datas;
	}
	public void setDatas(Object datas) {
		this.datas = datas;
	}
	public Page getPageObj() {
		return pageObj;
	}
	public void setPageObj(Page pageObj) {
		this.pageObj = pageObj;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
