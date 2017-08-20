package com.atguigu.atcrowdfunding.common;

import java.util.List;

/**
 * 数据包装类
 * @author 18801
 *
 */
public class Datas {

	private List<User> datas;
	private List<Integer> ids;
	private List<CertImg> certimgs;

	public List<CertImg> getCertimgs() {
		return certimgs;
	}

	public void setCertimgs(List<CertImg> certimgs) {
		this.certimgs = certimgs;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<User> getDatas() {
		return datas;
	}

	public void setDatas(List<User> datas) {
		this.datas = datas;
	}
}
